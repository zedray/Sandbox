package com.zedray.box;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import com.zedray.box.Piece.Type;

/***
 * 3D Cube puzzle using depth first search.
 *
 * Generates every possible solution to the given Cube Puzzle and outputs to
 * file.  The challenge is to generate solutions quickly, and to avoid
 * producing duplicate solutions (due to Cube rotation, identical piece
 * swapping, inefficient search strategy, etc).
 *
 * This solution attempts every possible Cube fill solution, but "cheats" in a
 * number of important ways in order to complete quickly:
 *
 * - Space priority.
 *   A Cube must have its internal space filled up in a specific order.  This
 *   optimisation rejects a large number of valid solutions from every
 *   sub-tree, as they can always be reached in other ways.
 *
 * - Non-rotating Piece.
 *   A specific Piece (namely the "S") is chosen to go through fewer
 *   transformations.  This means for all solutions it is essentially "locked"
 *   in place (i.e. only moving to the center or the edge, and never rotating),
 *   resulting in fewer duplicate sub-tree searches.
 *
 * - Depth first search.
 *   Storing the entire graph in memory is prohibitively expensive, which is
 *   why a depth first search strategy is preferable.  Cube nodes with fewer
 *   Pieces remaining take higher priority than their emptier peers, meaning
 *   lower parts of the tree are quickly executed and discarded.  Despite the
 *   huge size of the problem space, the queue averages around 500 in-memory
 *   Cube search notes.
 *
 * - Piece Transformation Caching.
 *   Generating transformations for each Piece is expensive (I picked up this
 *   tip from the Java Profiler: "JVM Monitor").  It's much more efficient to
 *   cache all the transformations for each shape in memory at the start, and
 *   just reject the options that don't fit.
 *
 * - Duplicate Filtering.
 *   As we have a "Non-rotating Piece" and each solution is a Cube of
 *   Piece.TYPE values, it's easy to deep compare each solution and remove
 *   duplicates.
 */
public final class FillACube {

    /** Cube volume. **/
    private static final int VOLUME = Cube.SIZE * Cube.SIZE * Cube.SIZE;
    /**
     * Array of pieces, sorted by Piece.Type.
     *
     * If Pieces of the same type appear sequentially, then we can avoid a
     * duplicate tree search by skipping to the next Piece type.
     **/
    private static final Piece[] PIECES = {
        new Piece(Piece.Type.L),
        new Piece(Piece.Type.L),
        new Piece(Piece.Type.L),
        new Piece(Piece.Type.L),
        new Piece(Piece.Type.S),
        new Piece(Piece.Type.T),
        new Piece(Piece.Type.R),
    };

    /***
     * Private constructor.
     */
    private FillACube() {
        // Do nothing.
    }

    /***
     * Main entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) {
        System.out.println("Start " + FillACube.class.getName());
        final long time = System.currentTimeMillis();
        List<Cube> results = getFilledCubes(new Cube(PIECES.length));
        System.out.println("[" + results.size()
                + "] solutions written to file.");
        System.out.println("Done in " + (System.currentTimeMillis() - time)
                + "ms.");

        /** Write solutions to file. **/
        try {
            final FileWriter fstream = new FileWriter("solution_"
                    + results.size() + ".txt");
            final BufferedWriter out = new BufferedWriter(fstream);
            out.write("[" + results.size() + "] x solutions\n\n");
            for (final Cube cube : results) {
                out.write(cube.toString());
            }
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * Comparator for comparing nodes.
     *
     * To reduce the total memory footprint:  Choose the path with the fewest
     * number of remaining pieces left first, then consider other options
     * later.
     *
     * Also, remove duplicate notes as they are unnecessary.
     */
    public static class CubeComparator implements Comparator<Cube> {

        @Override
        public final int compare(final Cube cube1, final Cube cube2) {
            if (cube1.equals(cube2)) {
                return 0;
            } else if (cube1.getNumberOfRemainingPieces()
                    < cube2.getNumberOfRemainingPieces()) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    /***
     * Return the given cube filled completely with the given pieces.
     *
     * @param cube Shape to fill.
     * @return Filled Cube.
     */
    private static List<Cube> getFilledCubes(final Cube cube) {

        final List<Cube> result = new ArrayList<Cube>();
        final TreeSet<Cube> priorityQueue
            = new TreeSet<Cube>(new CubeComparator());
        priorityQueue.add(cube);

        while (!priorityQueue.isEmpty()) {
            final Cube top = priorityQueue.pollFirst();

            /** Add the solution to the result if the cube is complete. **/
            if (top.getFilledVolume() == VOLUME) {
                if (!result.contains(top)) {
                    result.add(top);
                }
                continue;
            }

            /** Generate next set of fill options and add them to queue. **/
            Type lastType = null;
            for (final int piece : top.getRemainingPieces()) {

                /** Ignore duplicate types. **/
                if (lastType == PIECES[piece].getType()) {
                    continue;

                } else {
                    lastType = PIECES[piece].getType();
                }

                /** Check all Piece transformations. **/
                for (final boolean[][][] item
                        : PIECES[piece].getType().getPossible3DPieces()) {

                    /**
                     * Check if Piece transformation fits and is in fill
                     * priority order.
                     **/
                    if (top.isFit(item) && top.isPriority(item)) {
                        final Cube newCube = new Cube(top);
                        newCube.addPiece(piece,
                                PIECES[piece].getType().ordinal(), item);
                        priorityQueue.add(newCube);
                    }
                }
            }
        }

        /** Return solutions. **/
        return result;
    }
}

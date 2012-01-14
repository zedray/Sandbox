package com.zedray.box;

/***
 * Cube.
 */
public class Cube {

    /** Cube size. **/
    public static final int SIZE = 3;

    /** 3D space inside cube. **/
    private int[][][] mCube = new int[SIZE][SIZE][SIZE];
    /** Index of remaining Pieces from PIECES array. **/
    private int[] mRemainingPieces;

    /***
     * Constructor.
     *
     * @param pieces Number of remaining pieces.
     */
    public Cube(final int pieces) {
        mRemainingPieces = new int[pieces];
        for (int i = 0; i < pieces; i++) {
            mRemainingPieces[i] = i;
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    mCube[i][j][k] = -1;
                }
            }
        }
    }

    /***
     * Cloning constructor.
     *
     * @param cube Object to deep copy.
     */
    public Cube(final Cube cube) {
        final int[] remainingPieces = cube.getRemainingPieces();
        mRemainingPieces = new int[remainingPieces.length];
        for (int i = 0; i < remainingPieces.length; i++) {
            mRemainingPieces[i] = remainingPieces[i];
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    mCube[i][j][k] = cube.mCube[i][j][k];
                }
            }
        }
    }

    /***
     * Return TRUE if the given item fills the next empty space with the
     * highest priority, FALSE otherwise.
     *
     * Every space inside the cube is assigned a specific priority, so when
     * adding Pieces to the cube we ignore options that leave an empty
     * space with a higher priority empty.  This means we only find a
     * solution in a specific order, and avoid repeating the search in a
     * different order.
     *
     * @param item Item to test.
     * @return TRUE if the given item fills the next empty space with the
     *          highest priority, FALSE otherwise.
     */
    public final boolean isPriority(final boolean[][][] item) {

        /** Search Cube in specific order. **/
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {

                    /** Found first empty space. **/
                    if (mCube[i][j][k] == -1) {
                        return item[i][j][k];
                    }
                }
            }
        }

        /** No empty spaces found (should not be reached). **/
        return false;
    }

    /***
     * Add a piece to this Cube.
     *
     * @param index Index of given Piece.
     * @param type Piece type.
     * @param piece Piece to add.
     */
    public final void addPiece(final int index, final int type,
            final boolean[][][] piece) {

        final int[] remainingPieces = new int[mRemainingPieces.length - 1];
        int pos = 0;
        for (int i = 0; i < remainingPieces.length; i++) {
            if (index == mRemainingPieces[i]) {
                pos += 1;
            }
            remainingPieces[i] = mRemainingPieces[pos];
            pos += 1;
        }
        mRemainingPieces = remainingPieces;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    if (piece[i][j][k]) {
                        mCube[i][j][k] = type;
                    }
                }
            }
        }
    }

    /***
     * Return TRUE if the given Piece can fit into this Cube, FALSE
     * otherwise.
     *
     * @param piece Piece to check.
     * @return TRUE if the given Piece can fit into this Cube, FALSE
     *          otherwise.
     */
    public final boolean isFit(final boolean[][][] piece) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    if (piece[i][j][k] && mCube[i][j][k] != -1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /***
     * Return the filled volume of this Cube.
     *
     * @return Filled volume of this Cube.
     */
    public final int getFilledVolume() {
        int result = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    if (mCube[i][j][k] != -1) {
                        result += 1;
                    }
                }
            }
        }
        return result;
    }

    /***
     * Return TRUE if given Cube contents is equal to the current Cube,
     * FALSE otherwise.
     *
     * @param cube Cube object for comparison.
     * @return TRUE if given Cube contents is equal to the current Cube,
     * FALSE otherwise.
     */
    @Override
    public final boolean equals(final Object cube) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    if (mCube[i][j][k] != ((Cube) cube).mCube[i][j][k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /***
     * Return the Object hash code.
     *
     * @return Object hash code.
     */
    @Override
    public final int hashCode() {
        return mCube.hashCode();
    }

    /***
     * Get Array of remaining Pieces index.
     *
     * @return Array of remaining Pieces index.
     */
    public final int[] getRemainingPieces() {
        return mRemainingPieces;
    }

    /***
     * Get number of remaining Pieces.
     *
     * @return Number of remaining Pieces.
     */
    public final int getNumberOfRemainingPieces() {
        return mRemainingPieces.length;
    }

    /***
     * Return Cube as a String.
     *
     * @return Cube as a String.
     */
    public final String toString() {
        final Piece.Type[] types = Piece.Type.values();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append("[" + types[mCube[i][j][0]] + "]["
                        + types[mCube[i][j][1]] + "]["
                        + types[mCube[i][j][2]] + "]  ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        return sb.toString();
    }
}
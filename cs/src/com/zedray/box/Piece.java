package com.zedray.box;

/***
 * Piece.
 */
public class Piece {

    /** Number of rotations required along an axis. **/
    private static final int ROTATIONS = 4;
    /** Type of shape. **/
    public enum Type {
        /**
         * Tetris L.    #
         *              ###
         **/
        L(new boolean[][]{{true, true}, {true, false}, {true, false}}, true),
        /**
         * Tetris S.     ##
         *              ##
         **/
        S(new boolean[][]{{true, false}, {true, true}, {false, true}}, false),
        /**
         * Tetris T.     #
         *              ###
         **/
        T(new boolean[][]{{true, false}, {true, true}, {true, false}}, true),
        /**
         * Right angle.  #
         *              ##
         **/
        R(new boolean[][]{{true, true}, {true, false}}, true);

        /** Cache of Possible 3D Pieces. **/
        private boolean[][][][] mPossible3DPieces = null;

        /***
         * Constructor.
         *
         * @param piece Piece as a two dimensional array.
         * @param rotate TRUE if Piece can be rotated, FALSE otherwise.
         */
        private Type(final boolean[][] piece, final boolean rotate) {
            mPossible3DPieces = Piece.getPossible3DPieces(piece, rotate);
        }

        /***
         * Return all possible orientations and positions of this Piece in the
         * cube.  Cache results for the current type.
         *
         * @return All possible orientations and positions of this Piece in the
         *          cube.
         */
        public boolean[][][][] getPossible3DPieces() {
            return mPossible3DPieces;
        }
    }

    /** Piece in 2D space. **/
    private Type mType;

    /***
     * Constructor.
     *
     * @param type Piece type.
     */
    public Piece(final Type type) {
        mType = type;
    }

    /***
     * Return all possible orientations and positions of this Piece in the
     * cube.
     *
     * @param piece Piece as a two dimensional array.
     * @param rotate TRUE if Piece can be rotated, FALSE otherwise.
     * @return All possible orientations and positions of this Piece in the
     *          cube.
     */
    public static final boolean[][][][] getPossible3DPieces(
            final boolean[][] piece, final boolean rotate) {
        final int w = Cube.SIZE - piece.length + 1;
        final int h;
        if (rotate) {
            h = Cube.SIZE - piece[0].length + 1;
        } else {
            h = 1;
        }

        final int d;
        if (rotate) {
            d = Cube.SIZE;
        } else {
            d = (Cube.SIZE / 2) + 1;
        }

        final int size;
        if (rotate) {
            size =  w * h * d * ROTATIONS * ROTATIONS * ROTATIONS;
        } else {
            size =  w * h * d;
        }

        final boolean[][][][] result
            = new boolean[size][Cube.SIZE][Cube.SIZE][Cube.SIZE];

        /** Generate all the possible offsets. **/
        int pos = 0;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                for (int k = 0; k < d; k++) {
                    final boolean[][][] offsetPiece
                        = getTransform(piece, i, j, k);
                    if (rotate) {
                        for (int x = 0; x < ROTATIONS; x++) {
                            for (int y = 0; y < ROTATIONS; y++) {
                                for (int z = 0; z < ROTATIONS; z++) {
                                    result[pos] = rotate(offsetPiece, x, y, z);
                                    pos += 1;
                                }
                            }
                        }
                    } else {
                        result[pos] = offsetPiece;
                        pos += 1;
                    }
                }
            }
        }

        return result;
    }

    /***
     * Return a deep copy of the given Piece rotated along X and Y axis a
     * given number of times.
     *
     * Warning 87% of time is spent here!!
     *
     * @param piece Piece to return.
     * @param x X-Axis rotation (multiple of 90 degree segments).
     * @param y Y-Axis rotation (multiple of 90 degree segments).
     * @param z Z-Axis rotation (multiple of 90 degree segments).
     * @return Deep copy of the given Piece rotated along X and Y axis a
     *          given number of times.
     */
    public static boolean[][][] rotate(final boolean[][][] piece,
            final int x, final int y, final int z) {

        /** Rotate X-Axis (i.e. swap YZ values). **/
        final boolean[][][] xRotation
            = new boolean[Cube.SIZE][Cube.SIZE][Cube.SIZE];
        for (int i = 0; i < Cube.SIZE; i++) {
            for (int j = 0; j < Cube.SIZE; j++) {
                for (int k = 0; k < Cube.SIZE; k++) {
                    switch (x) {
                    case 0:
                        xRotation[i][j][k] = piece[i][j][k];
                        break;
                    case 1: // 90 Degrees.
                        xRotation[i][j][k] = piece[i][Cube.SIZE - k - 1][j];
                        break;
                    case 2: // 180 Degrees.
                        xRotation[i][j][k] = piece[i][Cube.SIZE - j - 1
                                                      ][Cube.SIZE - k - 1];
                        break;
                    default: // 270 Degrees.
                        xRotation[i][j][k] = piece[i][k][Cube.SIZE - j - 1];
                    }
                }
            }
        }

        /** Rotate Y-Axis (i.e. swap ZX values). **/
        final boolean[][][] yRotation
            = new boolean[Cube.SIZE][Cube.SIZE][Cube.SIZE];
        for (int i = 0; i < Cube.SIZE; i++) {
            for (int j = 0; j < Cube.SIZE; j++) {
                for (int k = 0; k < Cube.SIZE; k++) {
                    switch (y) {
                    case 0:
                        yRotation[i][j][k] = xRotation[i][j][k];
                        break;
                    case 1: // 90 Degrees.
                        yRotation[i][j][k] = xRotation[Cube.SIZE - k - 1][j][i];
                        break;
                    case 2: // 180 Degrees.
                        yRotation[i][j][k] = xRotation[Cube.SIZE - i - 1
                                                       ][j][Cube.SIZE - k - 1];
                        break;
                    default: // 270 Degrees.
                        yRotation[i][j][k] = xRotation[k][j][Cube.SIZE - i - 1];
                    }
                }
            }
        }

        /** Rotate Z-Axis (i.e. swap XY values). **/
        final boolean[][][] zRotation
            = new boolean[Cube.SIZE][Cube.SIZE][Cube.SIZE];
        for (int i = 0; i < Cube.SIZE; i++) {
            for (int j = 0; j < Cube.SIZE; j++) {
                for (int k = 0; k < Cube.SIZE; k++) {
                    switch (z) {
                    case 0:
                        zRotation[i][j][k] = yRotation[i][j][k];
                        break;
                    case 1: // 90 Degrees.
                        zRotation[i][j][k] = yRotation[Cube.SIZE - j - 1][i][k];
                        break;
                    case 2: // 180 Degrees.
                        zRotation[i][j][k] = yRotation[Cube.SIZE - i - 1
                                                       ][Cube.SIZE - j - 1][k];
                        break;
                    default: // 270 Degrees.
                        zRotation[i][j][k] = yRotation[j][Cube.SIZE - i - 1][k];
                    }
                }
            }
        }

        return zRotation;
    }

    /***
     * Return the Piece transformed in space with the given three
     * dimensional offsets.
     *
     * @param piece Piece to rotate.
     * @param x X offset.
     * @param y Y offset.
     * @param z Z offset.
     * @return Transformed Piece;
     */
    public static final boolean[][][] getTransform(final boolean[][] piece,
            final int x, final int y, final int z) {
        final boolean[][][] result
            = new boolean[Cube.SIZE][Cube.SIZE][Cube.SIZE];

        for (int i = 0; i < Cube.SIZE; i++) {
            for (int j = 0; j < Cube.SIZE; j++) {
                for (int k = 0; k < Cube.SIZE; k++) {
                    result[i][j][k] = false;
                }
            }
        }

        final int w = piece.length;
        final int h = piece[0].length;
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (piece[i][j]) {
                    result[x + i][y + j][z] = true;
                }
            }
        }

        return result;
    }

    /***
     * Return the Piece type.
     *
     * @return Piece type.
     */
    public final Type getType() {
        return mType;
    }
}
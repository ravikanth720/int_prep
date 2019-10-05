package algorithms;

public class FloodFill {

        public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
            if (!notOutOfBounds(image, sr, sc)) return image;
            int color = image[sr][sc];
            image[sr][sc] = newColor;

            if (notOutOfBounds(image, sr, sc + 1) && image[sr][sc+1] == color) image = floodFill(image, sr, sc+1, newColor);
            if (notOutOfBounds(image, sr, sc - 1) && image[sr][sc-1] == color) image = floodFill(image, sr, sc-1, newColor);
            if (notOutOfBounds(image, sr + 1, sc) && image[sr+1][sc] == color) image = floodFill(image, sr+1, sc, newColor);
            if (notOutOfBounds(image, sr - 1, sc) && image[sr-1][sc] == color) image = floodFill(image, sr-1, sc, newColor);

            return image;
        }

        private boolean notOutOfBounds(int[][] arr, int i, int j) {
            if (arr == null || arr.length == 0) return false;
            if (i < 0 || i > arr.length - 1) return false;
            if (j < 0 || j > arr[0].length - 1) return false;

            return true;
        }

        public static void main(String[] args) {
            FloodFill f = new FloodFill();
            int[][] img = new int[][]{new int[]{1,1,1}, new int[]{1,1,0}, new int[]{1,0,1}};
            f.floodFill(img, 1,1,1);
        }
}

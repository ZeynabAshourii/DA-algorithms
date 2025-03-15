package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ClosestTwoPoints {
    public static class Point {
        public long x;
        public long y;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
    public static class TwoPoints{
        public Point point1;
        public Point point2;
        TwoPoints(Point point1 , Point point2){
            this.point1 = point1;
            this.point2 = point2;
        }
        public float dis(){
            return distance(point1 , point2);
        }
        public void print(){
            if (point1.x <= point2.x){
                System.out.println(point1.x + " " + point1.y + " " + point2.x + " " + point2.y);
            }else {
                System.out.println(point2.x + " " + point2.y + " " + point1.x + " " + point1.y);
            }
        }
    }
    public static float distance(Point p1, Point p2) {
        return (float) Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) +
                (p1.y - p2.y) * (p1.y - p2.y));
    }
    public static TwoPoints stripClosest(Point[] strip, int size, float d) {
        TwoPoints best = null;
        float min = d;
        for (int i = 0; i < size; ++i) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; j++) {
                if (distance(strip[i], strip[j]) < min) {
                    min = distance(strip[i], strip[j]);
                    best = new TwoPoints(strip[i] , strip[j]);
                }
            }
        }

        return best;
    }
    public static TwoPoints closestUtil(Point[] pointsX,Point[] pointsY) {
        TwoPoints best = null;

        int n = pointsX.length;

        if (n <= 3) {
            float min = Float.MAX_VALUE;
            float currMin = 0;

            for (int i = 0; i < n; ++i) {
                for (int j = i + 1; j < n; ++j) {
                    currMin = distance(pointsX[i], pointsX[j]);
                    if (currMin < min) {
                        min = currMin;
                        best = new TwoPoints(pointsX[i] , pointsX[j]);
                    }
                }
            }
            return best;
        }

        int mid = n / 2;
        Point midPoint = pointsX[mid];

        Point[] leftHalf = Arrays.copyOfRange(pointsX, 0, mid);
        Point[] rightHalf = Arrays.copyOfRange(pointsX, mid, n);

        Point[] leftSortedByY = new Point[mid];
        Point[] rightSortedByY = new Point[n - mid];
        int li = 0, ri = 0;
        for (Point p : pointsY) {
            if (p.x < midPoint.x) {
                leftSortedByY[li++] = p;
            } else {
                rightSortedByY[ri++] = p;
            }
        }

        TwoPoints bl = closestUtil(leftHalf, leftSortedByY);
        TwoPoints br = closestUtil(rightHalf, rightSortedByY);

        float dl = bl.dis();
        float dr = br.dis();

        float d = Math.min(dl, dr);
        if(dl < dr){
            best = bl;
        }else {
            best = br;
        }

        Point[] strip = new Point[n];
        int stripSize = 0;;
        for (Point p : pointsY) {
            if (Math.abs(p.x - midPoint.x) < d) {
                strip[stripSize++] = p;
            }
        }

        TwoPoints ans = stripClosest(strip, stripSize, d);

        if (ans != null && ans.dis() < best.dis()){
            best = ans;
        }
        return best;
    }

    static void sortY(Point points[], int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            sortY(points, l, m);
            sortY(points, m + 1, r);

            mergeY(points, l, m, r);
        }
    }

    static void mergeY(Point points[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Point L[] = new Point[n1];
        Point R[] = new Point[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = points[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = points[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].y <= R[j].y) {
                points[k] = L[i];
                i++;
            } else {
                points[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            points[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            points[k] = R[j];
            j++;
            k++;
        }
    }

    static void sortX(Point points[], int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            sortX(points, l, m);
            sortX(points, m + 1, r);

            mergeX(points, l, m, r);
        }
    }

    static void mergeX(Point points[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Point L[] = new Point[n1];
        Point R[] = new Point[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = points[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = points[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].x <= R[j].x) {
                points[k] = L[i];
                i++;
            } else {
                points[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            points[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            points[k] = R[j];
            j++;
            k++;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        int n =  Integer.parseInt(reader.readLine());
        Point[] pointsX = new Point[n];
        Point[] pointsY = new Point[n];
        for (int i = 0; i < n; i++) {
            String[] strings = reader.readLine().split(" ");
            long x = Integer.parseInt(strings[0]);
            long y = Integer.parseInt(strings[1]);
            pointsX[i] = new Point(x, y);
            pointsY[i] = new Point(x, y);
        }
        sortX(pointsX , 0 , n-1);

        sortY(pointsY , 0 , n-1);

        closestUtil(pointsX , pointsY).print();
    }
}

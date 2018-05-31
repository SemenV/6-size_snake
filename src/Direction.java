
import javafx.util.Pair;

    /*
    nw N ne  !!!use only nw ne
    W  +  E  !!!w e
    sw S se  !!! sw se
     */
public enum Direction {

    NW {
        @Override
        public Pair<Integer, Integer> getStep(Pair<Integer, Integer> x) {
            int column = x.getKey();
            int row = x.getValue();
            column--;
            if (column % 2 == 0) row--;
            return new Pair<>(column % columnCount,row % rowCount);
        }
    }, NE {
        @Override
        public Pair<Integer, Integer> getStep(Pair<Integer, Integer> x) {
            int column = x.getKey();
            int row = x.getValue();
            column--;
            if (column % 2 == 1) row++;
            return new Pair<>(column % columnCount,row % rowCount);

        }
    }, W {
        @Override
        public Pair<Integer, Integer> getStep(Pair<Integer, Integer> x) {
            return new Pair<>(x.getKey() % columnCount,(x.getValue() - 1) % rowCount);
        }
    }, E {
        @Override
        public Pair<Integer, Integer> getStep(Pair<Integer, Integer> x) {
            return new Pair<>(x.getKey() % columnCount ,(x.getValue() + 1) % rowCount);
        }
    }, SW {
        @Override
        public Pair<Integer, Integer> getStep(Pair<Integer, Integer> x) {
            int column = x.getKey();
            int row = x.getValue();
            column++;
            if (column % 2 == 0) row--;
            return new Pair<>(column % columnCount,row % rowCount);
        }
    }, SE {
        @Override
        public Pair<Integer, Integer> getStep(Pair<Integer, Integer> x) {
            int column = x.getKey();
            int row = x.getValue();
            column++;
            if (column % 2 == 1) row++;
            return new Pair<>(column % columnCount,row % rowCount);
        }
    };

        public abstract Pair<Integer,Integer> getStep(Pair<Integer, Integer> x);

        private static int rowCount = 10;
        private static int columnCount = 10;

}

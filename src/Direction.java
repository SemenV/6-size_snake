
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
            return new Pair<>(column ,row );
        }
    }, NE {
        @Override
        public Pair<Integer, Integer> getStep(Pair<Integer, Integer> x) {
            int column = x.getKey();
            int row = x.getValue();
            column--;
            if (column % 2 == 1) row++;
            return new Pair<>(column ,row );

        }
    }, W {
        @Override
        public Pair<Integer, Integer> getStep(Pair<Integer, Integer> x) {
            return new Pair<>(x.getKey() ,(x.getValue() - 1) );
        }
    }, E {
        @Override
        public Pair<Integer, Integer> getStep(Pair<Integer, Integer> x) {
            return new Pair<>(x.getKey() ,(x.getValue() + 1) );
        }
    }, SW {
        @Override
        public Pair<Integer, Integer> getStep(Pair<Integer, Integer> x) {
            int column = x.getKey();
            int row = x.getValue();
            column++;
            if (column % 2 == 0) row--;
            return new Pair<>(column ,row );
        }
    }, SE {
        @Override
        public Pair<Integer, Integer> getStep(Pair<Integer, Integer> x) {
            int column = x.getKey();
            int row = x.getValue();
            column++;
            if (column % 2 == 1) row++;
            return new Pair<>(column ,row );
        }
    };

        public abstract Pair<Integer,Integer> getStep(Pair<Integer, Integer> x);

        private static int rowCount = 10;
        private static int columnCount = 10;

}

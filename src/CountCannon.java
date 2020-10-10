public enum CountCannon {
    Two,
    Four,
    Six;

    public static CountCannon getCount(int digit) {
        switch (digit) {
            case 2:
                return CountCannon.Two;
            case 4:
                return CountCannon.Four;
            case 6:
                return CountCannon.Six;
        }
        return null;
    }
}

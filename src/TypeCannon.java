public enum TypeCannon {
    First,
    Second,
    Third;

    public static TypeCannon getType(int digit) {
        switch (digit) {
            case 1:
                return TypeCannon.First;
            case 2:
                return TypeCannon.Second;
            case 3:
                return TypeCannon.Third;
        }
        return null;
    }
}

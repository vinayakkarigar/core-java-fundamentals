package com.modern.refresh;

import java.util.function.Function;
import java.util.function.Supplier;

public class Expr {

    /* Example: Number(5) */
    public static class NumExpr extends Expr {
        private int value;

        public NumExpr(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "NumExpr{" +
                    "value=" + value +
                    '}';
        }
    }

    /*
     Example: BiOps( "+", Number(0), Number(5))
     */
    public static class BiOps extends Expr {
        private String operation;
        private Expr left;
        private Expr right;

        public BiOps(String operation, Expr left, Expr right) {
            this.operation = operation;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "( " + left + " " + operation + " " + right + " )";
        }
    }

    public static interface TriFunction<S, T, U, R> {
        R apply(S s, T t,  U u);
    }

    public static <T extends Expr> T  patternMatching(Expr e, TriFunction<String, Expr, Expr, T> biopsCase,
                                      Function<Integer, T> numCase, Supplier<T> defaultCase) {
        return (e instanceof BiOps) ? biopsCase.apply(((BiOps) e).operation, ((BiOps) e).left, ((BiOps) e).right)
                : (e instanceof NumExpr) ? numCase.apply(((NumExpr) e).value)
                : defaultCase.get();
    }


    public static Expr simplifyExpr(Expr e) {
         TriFunction<String, Expr, Expr, Expr> biopsCase =
                (ops, left, right) -> {
                    if ("+".equals(ops)) {
                        if (left instanceof NumExpr && ((NumExpr) left).value == 0) {
                            return right;
                        } else if (right instanceof NumExpr && ((NumExpr) right).value == 0) {
                            return left;
                        }

                    }
                    if ("*".equals(ops)) {
                        if (left instanceof NumExpr && ((NumExpr) left).value == 1) {
                            return right;
                        } else if (right instanceof NumExpr && ((NumExpr) right).value == 1) {
                            return left;
                        }
                    }
                    return new BiOps(ops, left, right);
                };
         Function<Integer, Expr> numCase =
                (v) -> {
                    return new NumExpr(v);
                };
         Supplier<Expr> defaultCase =
                () -> new Expr();

        return patternMatching(e, biopsCase, numCase, defaultCase);
    }


    public static void main(String[] args) {
        final BiOps biOps = new BiOps("*", new NumExpr(1), new NumExpr(5));
        final Expr expr = Expr.simplifyExpr(biOps);
        System.out.println(expr);

    }


}

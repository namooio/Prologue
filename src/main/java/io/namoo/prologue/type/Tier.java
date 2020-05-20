/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package io.namoo.prologue.type;

public enum Tier {
    //
	Primary,
    Secondary,
    Third;

    public boolean isPrimary() {
        //
        return this.equals(Primary);
    }

    public boolean isSecondary() {
        //
        return this.equals(Secondary);
    }

    public boolean isThird() {
        //
        return this.equals(Third);
    }

    public static void main(String[] args) {
        //
        Tier tier = Tier.Primary;
        System.out.println(tier.isPrimary());
    }
}
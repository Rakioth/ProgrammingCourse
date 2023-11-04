package com.raks.psp.example07;

public class Deadlock {

    static class Friend {

        private final String _name;

        public Friend(String name) {
            _name = name;
        }

        public String getName() {
            return _name;
        }

        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s has bowed to me!%n", _name, bower.getName());
            bower.bowBack(this);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s has bowed back to me!%n", _name, bower.getName());
        }

    }

    public static void main(String[] args) {
        Friend alphonse = new Friend("Alphonse");
        Friend gaston   = new Friend("Gaston");
        new Thread(() -> alphonse.bow(gaston)).start();
        new Thread(() -> gaston.bow(alphonse)).start();
    }

}
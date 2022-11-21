package io.github.justfoxx.app.db;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public List<Account> accounts = new ArrayList<>();
    public static class Account {
        public int i = 0;
        public int level = 1;
        public String name = "Player";
    }
}

package com.thoughtworks;

import java.util.*;
import java.util.stream.Stream;

public class App {

  public static void main(String[] args) {
    // 以下是执行交易的交易员和发生的一系列交易,请为以下八个查询方法提供实现。
    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");
    List<Transaction> transactions = Arrays.asList(
        new Transaction(brian, 2011, 300),
        new Transaction(raoul, 2012, 1000),
        new Transaction(raoul, 2011, 400),
        new Transaction(mario, 2012, 710),
        new Transaction(mario, 2012, 700),
        new Transaction(alan, 2012, 950)
    );

    // 1.找出2011年的所有交易并按交易额排序(从低到高)
    System.out.println(get2011Transactions(transactions));

    // 2.交易员都在哪些不同的􏱜城市工作过
    System.out.println(getTradersCity(transactions));

    // 3.查找所有来自于剑桥的交易员，并按姓名排序
    System.out.println(getCambridgeTraders(transactions));

    // 4.返回所有交易员的姓名字符串，按字母顺序排序
    System.out.println(getTradersName(transactions));

    // 5.有没有交易员是在米兰工作的
    System.out.println(hasMilanTrader(transactions));

    // 6.返回交易员是剑桥的所有交易的交易额
    System.out.println(getCambridgeTransactionsValue(transactions));

    // 7.所有交易中，最高的交易额是多少
    System.out.println(getMaxTransactionValue(transactions));

    // 8.返回交易额最小的交易
    System.out.println(getMinTransaction(transactions));
  }

  public static List<Transaction> get2011Transactions(List<Transaction> transactions) {
    Stream<Transaction> stream1 = transactions.stream();
    Stream<Transaction> stream2 = stream1.filter(transaction -> transaction.getYear() == 2011);
    List<Transaction> list = new ArrayList<>();
    stream2.forEach(transaction -> list.add(transaction));
    Collections.sort(list, new Comparator<Transaction>() {
      @Override
      public int compare(Transaction o1, Transaction o2) {
        return o1.getValue() - o2.getValue();
      }
    });
    return list;
  }

  public static List<String> getTradersCity(List<Transaction> transactions) {
    Stream<Transaction> stream1 = transactions.stream();
    Set<String> set = new HashSet<>();
    stream1.forEach(transaction -> set.add(transaction.getTrader().getCity()));
    List<String> list = new ArrayList<>();
    for (String s : set) {
      list.add(s);
    }
    return list;
  }

  public static List<Trader> getCambridgeTraders(List<Transaction> transactions) {
    Stream<Transaction> stream1 = transactions.stream();
    Stream<Transaction> stream2 = stream1.filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"));
    List<Trader> list = new ArrayList<>();
    stream2.forEach(transaction -> list.add(transaction.getTrader()));
    /*Collections.sort(list, new Comparator<Trader>() {
      @Override
      public int compare(Trader o1, Trader o2) {
        return Integer.valueOf(o1.getName()) - Integer.valueOf(o2.getName());
      }
    });*/
    return list;
  }

  public static List<String> getTradersName(List<Transaction> transactions) {
    Stream<Transaction> stream1 = transactions.stream();
    List<String> list = new ArrayList<>();
    stream1.forEach(transaction -> list.add(transaction.getTrader().getName()));
    Collections.sort(list);
    return list;
  }

  public static boolean hasMilanTrader(List<Transaction> transactions) {
    Stream<Transaction> stream1 = transactions.stream();
    Stream<Transaction> stream2 = stream1.filter(transaction -> transaction.getTrader().getCity().equals("Milan"));
    return stream2.count() > 0;
  }

  public static List<Integer> getCambridgeTransactionsValue(List<Transaction> transactions) {
    Stream<Transaction> stream1 = transactions.stream();
    Stream<Transaction> stream2 = stream1.filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"));
    List<Integer> list = new ArrayList<>();
    stream2.forEach(transaction -> list.add(transaction.getValue()));
    return list;
  }

  public static int getMaxTransactionValue(List<Transaction> transactions) {
    Stream<Transaction> stream = transactions.stream();
    List<Integer> list = new ArrayList<>();
    stream.forEach(transaction -> list.add(transaction.getValue()));
    Collections.sort(list);
    return list.get(list.size() - 1);
  }

  public static Transaction getMinTransaction(List<Transaction> transactions) {
    Stream<Transaction> stream = transactions.stream();
    List<Transaction> list = new ArrayList<>();
    stream.forEach(transaction -> list.add(transaction));
    Collections.sort(list, new Comparator<Transaction>() {
      @Override
      public int compare(Transaction o1, Transaction o2) {
        return o1.getValue() - o2.getValue();
      }
    });
    return list.get(0);
  }
}

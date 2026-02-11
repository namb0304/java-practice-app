// アプリが起動した瞬間にだけ働くプログラムです。

package com.example.demo;

import org.springframework.boot.CommandLineRunner; // 「起動時に走る人」という機能を輸入
import org.springframework.stereotype.Component;

@Component // 「このクラスもSpring Bootの仲間に入れてね（起動時に読み込んでね）」という印
public class DataLoader implements CommandLineRunner {

    private final BookRepository bookRepository; // 倉庫係を用意

    // コンストラクタで倉庫係を受け取る
    public DataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override // 「起動時に実行する内容をここに書くよ！」という宣言
    public void run(String... args) throws Exception {
        // ここに書いたコードが、アプリ起動時に1回だけ実行されます。
        
        // 1. データを作る
        Book book1 = new Book("ハリー・ポッターと賢者の石", "J.K.ローリング");
        // 2. 倉庫係に渡してDBに保存してもらう
        bookRepository.save(book1);

        Book book2 = new Book("走れメロス", "太宰治");
        bookRepository.save(book2);
        
        // ログ出し
        System.out.println("★テストデータを登録しました！");
    }
}
// 「データベースにどんな表（テーブル）を作るか」を決めるファイルです。

package com.example.demo;

import jakarta.persistence.Entity; // DBと連携するための輸入
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data; // 便利な道具（Lombok）の輸入

@Entity // ★重要: 「これを元に、DBに『Book』というテーブルを作れ！」という命令
@Data   // ★便利: 「getter/setter（データを出し入れする機能）」を自動で作れ！という命令
public class Book {

    @Id // 「これが背番号（主キー）だ！」という印
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 「番号は自動で 1, 2, 3... と増やしてね」という設定
    private Long id;

    private String title;  // 本のタイトルを保存する箱
    private String author; // 著者を保存する箱

    // 空っぽのコンストラクタ（Spring Bootが裏側で使うために必須）
    public Book() {
    }

    // 私たちがデータを作るときに使うコンストラクタ
    // new Book("タイトル", "著者") と書けるようにする
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
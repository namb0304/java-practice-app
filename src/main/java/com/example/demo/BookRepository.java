// ここが一番不思議な場所です。コードを書いていないのに機能します。

package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository; // Springの凄い機能を輸入
import org.springframework.stereotype.Repository;

@Repository // 「これはデータを操作する倉庫係（リポジトリ）ですよ」という看板
public interface BookRepository extends JpaRepository<Book, Long> {
    // ↑ ここが魔法！
    // 「JpaRepository」という「既に完成している凄い機能」を継承（コピー）しています。
    // <Book, Long> は「Bookというデータを、Long型のIDで管理するよ」という意味。
    
    // 中身は空っぽですが、これだけで以下の機能が全部使えます：
    // .save(book)   -> 保存する
    // .findAll()    -> 全件取ってくる
    // .findById(1)  -> IDが1のやつを探す
    // .delete(book) -> 削除する
}
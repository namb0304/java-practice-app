// ブラウザ（利用者）からのアクセスを最初に受け取る場所です。

package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin; // CORSの設定をするための輸入
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController // 「ここはWebの受付窓口（API）ですよ」という看板。これをつけるとデータの返信係になる。
@CrossOrigin(origins = "http://localhost:5173") // ★追加: 「http://localhost:5173 (React) からのアクセスは許可するよ」という意味
public class BookController {

    private final BookRepository bookRepository; // 倉庫係（マジックハンド）を用意

    // コンストラクタ（入社手続き）
    // Spring Bootが自動的に、さっき作った BookRepository をここに渡してくれます（DIと言います）
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books") // 「http://.../books という住所にアクセスが来たら、ここを実行して！」という看板
    public List<Book> getBooks() {
        // 倉庫係に「全部持ってきて！」と命令し、その結果をそのまま利用者に返します。
        // 自動的に JSON形式（[{}, {}]）に変換されます。
        return bookRepository.findAll();
    }
}
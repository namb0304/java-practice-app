package com.example.demo;

// 新しい機能（データを受け取るための機能）を輸入
import org.springframework.web.bind.annotation.PostMapping; // ★追加
import org.springframework.web.bind.annotation.RequestBody; // ★追加
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Reactからのアクセスを許可
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 1. 本の一覧を返す機能（GET）
    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    // 2. 新しい本を保存する機能（POST）★ここが新機能！
    // @PostMapping: 「データを送るから保存して！」というリクエストを受け付ける看板
    // @RequestBody: 「送られてきたデータ（JSON）を、BookというJavaの形に変換してね」という命令
    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        // 受け取った本を、マジックハンドを使ってDBに保存！
        return bookRepository.save(book);
    }
}
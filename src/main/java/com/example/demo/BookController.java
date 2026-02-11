package com.example.demo;

// 必要な機能を輸入
import org.springframework.web.bind.annotation.DeleteMapping; // ★追加: 削除機能用
import org.springframework.web.bind.annotation.PathVariable;  // ★追加: URLの数字(/1など)を受け取る用
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // Reactからのアクセス許可
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // ■ 1. 一覧取得 (GET)
    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    // ■ 2. 新規登録 (POST)
    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // ■ 3. 削除機能 (DELETE) ★ここが新機能！
    // -----------------------------------------------------------
    // @DeleteMapping("/books/{id}"): 
    //   「/books/1」や「/books/5」のように、URLの後ろに「ID」をつけてアクセスされたらここが動く。
    //   {id} は「ここは数字が入る場所だよ」という目印。
    //
    // @PathVariable Long id:
    //   URLの {id} の部分の数字を、Javaの変数「id」として受け取る。
    // -----------------------------------------------------------
    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        // マジックハンド(Repository)を使って、指定されたIDの本を削除する
        bookRepository.deleteById(id);
        
        // ログ出し（確認用）
        System.out.println("ID: " + id + " の本を削除しました！");
    }
}
package com.example.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; // ★追加: 更新機能用
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 1. 一覧取得 (GET)
    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    // 2. 新規登録 (POST)
    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // 3. 削除機能 (DELETE)
    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

    // ■ 4. 更新機能 (PUT) ★今回の新機能
    // -----------------------------------------------------------
    // @PutMapping("/books/{id}"):
    //   「/books/1」などのURLに「新しいデータ」と一緒にアクセスが来たらここが動く。
    //   PUT は「置き換え（更新）」という意味です。
    // -----------------------------------------------------------
    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book newBookData) {
        // 1. まず、IDを使って修正したい本をDBから探し出す
        // .orElse(null) は「もし見つからなかったら空っぽにしておく」という意味
        Book existingBook = bookRepository.findById(id).orElse(null);

        if (existingBook != null) {
            // 2. 見つかったら、中身（タイトルと著者）を新しいデータで書き換える
            existingBook.setTitle(newBookData.getTitle());
            existingBook.setAuthor(newBookData.getAuthor());
            
            // 3. 書き換えた内容で保存し直す（IDが同じなら上書き保存になる）
            return bookRepository.save(existingBook);
        }
        
        return null; // 本が見つからなかった場合は何もしない
    }
}
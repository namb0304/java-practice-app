# Java Webアプリ開発ハンズオン (Phase 1)

## 1. 成果物
* **構成:** React (Frontend) + Spring Boot (Backend)
* **通信:** HTTP通信 (REST API)
* **状態:** フロントからボタンを押すと、Javaサーバーが処理してレスポンスを返す。

## 2. 環境構築のポイント
* **JDK (Amazon Corretto):** Javaのエンジン。AWS版が安定していておすすめ。
* **Spring Initializr:** プロジェクトの「種」を作る必須ツール。手書きはしない。
* **pnpm:** 高速で容量に優しいパッケージマネージャ。`npm`の上位互換。

## 3. Javaとオブジェクト指向
* **クラス (Class):** 設計図。「たい焼きの型」。
* **インスタンス (Instance):** 実体。「焼けたたい焼き」。`new`で作る。
* **厳格なルール:**
    * ファイル名とクラス名は完全一致させる。
    * 文字列比較は `==` ではなく `.equals()` を使う（超重要）。

## 4. Web連携の壁「CORS」
* **現象:** React (5173) から Java (8080) を呼ぶとエラーになる。
* **理由:** ブラウザのセキュリティ機能。違う住所（ポート）へのアクセスは基本禁止。
* **解決策:** バックエンド（Java）側で許可証を出す。
```java
@CrossOrigin(origins = "http://localhost:5173")
public class HelloController { ... }
# SpringBoot + Spring Data JPA + Hibernateのサンプル

対象insertを行う時に、
* JpaRepositoryとEntityManagerで差異があるのか
* batch sizeを指定する/しないでの差
を確認するためだけのサンプルプログラム。

## 結果
### JpaRepository

 | 条件  | 時間（ms） |
 | ----- | ---- |
 | 毎回saveAndFlash（batch_size指定なし） | 21614 |
 | flushを最後だけ（batch_size指定なし） | 7037 |
 | 毎回saveAndFlash（batch_sizeに100を指定） | 22732 |
 | flushを最後だけ（batch_sizeに100を指定） | 7222 |

### EntityManager

 | 条件 | 時間（ms） |
 | ----- | ---- |
 | 毎回persistとflush（batch_size指定なし） | 18457 |
 | flushを最後だけ（batch_size指定なし） | 6897 |
 | 毎回saveAndFlash（batch_sizeに100を指定） | 20659 |
 | flushを最後だけ（batch_sizeに100を指定） | 7791 |

## 考察
JpaRepositoryよりはEntityManagerが速かった。
あと、一般的に速くなると言われているbatch sizeの指定が逆に遅くなったのは不思議。
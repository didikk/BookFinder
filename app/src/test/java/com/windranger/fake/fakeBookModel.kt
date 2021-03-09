package com.windranger.fake

import com.windranger.domain.models.BookModel

val fakeBookModel = BookModel(
    uid = "WxPHDwAAQBAJ",
    title = "Si Anak Pelangi",
    author = "Tere Liye",
    category = "Young Adult Fiction",
    publisher = "Tere Liye",
    pageCount = 674,
    rating = 3f,
    publishedDate = "2019-12-27",
    link = "http://books.google.co.id/books?id=WxPHDwAAQBAJ&printsec=frontcover&dq=tere+liye&hl=&cd=2&source=gbs_api",
    cover = "http://books.google.com/books/content?id=WxPHDwAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
    description = "Lucu. Seru. Menghibur. Buku ke-7 dari Serial Anak-Anak **Novel ini adalah naskah awal (asli) dari penulis; tanpa sentuhan editing, layout serta cover dari penerbit, dengan demikian, naskah ini berbeda dengan versi cetak, pun memiliki kelebihan dan kelemahan masing-masing."
)

val fakeBookList = listOf<BookModel>(fakeBookModel)
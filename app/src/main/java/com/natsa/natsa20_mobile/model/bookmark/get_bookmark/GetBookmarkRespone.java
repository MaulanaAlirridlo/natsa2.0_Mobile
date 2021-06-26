package com.natsa.natsa20_mobile.model.bookmark.get_bookmark;

public class GetBookmarkRespone {
    private Status status;
    private Bookmarks bookmarks;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Bookmarks getBookmarks() {
        return bookmarks;
    }

    public void setBookmarks(Bookmarks bookmarks) {
        this.bookmarks = bookmarks;
    }
}

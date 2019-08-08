package com.revision.ctci.gobjectorienteddesign;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class EOnlineBookReader {
    public static void main(String[] args) {
        /* All the classes are created as inner classes in order to avoid the error of name conflict please move the
         * classes outside in case if you plan to develop further from these classes */
        /* Major Modules Involved
         * Account
         * OnlineLibrary
         * PersonalLibrary
         * Book
         * ReadMode */
        EOnlineBookReader reader = new EOnlineBookReader();
    }

    /* User Account : Login module is to be made
     * Cart feature : for buying books seen in the online library
     * WishList feature : for later purchases
     * PersonalLibrary feature : going to the reading mode
     * Setting feature : personalize the environment */
    class Account {
        long id;
        String name;
        String email;
        Cart cart;
        WishList wishList;
        PersonalLibrary personalLibrary;
        List<Book> currentlyReading;
        AccountSetting setting;

        public Account(String name, String email) {
            this.id = getId(name);
            this.name = name;
            this.email = email;
            /* All the new object creation below can be postponed to the time when it is absolutely needed
             * by altering the setter method to check for null and the create one if null */
            this.cart = new Cart();
            this.wishList = new WishList();
            this.personalLibrary = new PersonalLibrary();
            this.currentlyReading = new ArrayList<>();
        }

        /* Used when data is built using database entry */
        public Account(long id, String name, String email, Cart cart, WishList wishList, PersonalLibrary personalLibrary, List<Book> currentlyReading, AccountSetting setting) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.cart = cart;
            this.wishList = wishList;
            this.personalLibrary = personalLibrary;
            this.currentlyReading = currentlyReading;
            this.setting = setting;
        }

        /* Fetch the available ID from Database after successful save*/
        private long getId(String name) {
            return 0;
        }

        public boolean addToCart(Book book) {
            return false;
        }

        public boolean addToCart(List<Book> books) {
            return false;
        }

        public void checkout() {

        }
    }

    class Cart {
        List<Book> books;

        public boolean addToCart(Book book) {
            return false;
        }

        public boolean addToCart(List<Book> books) {
            return false;
        }

        public boolean removeFromCart(Book book) {
            return false;
        }

        public boolean removeFromCart(List<Book> books) {
            return false;
        }

        public boolean addToWishList(Book book) {
            return false;
        }

        public boolean addToWishList(List<Book> books) {
            return false;
        }

        /* Proceed to checkout and then add the books to the personal library once the purchase is success and remove from cart */
        public boolean performPurchase() {
            return false;
        }
    }

    class WishList {
        List<Book> books;

        public boolean addToWishList(Book book) {
            return false;
        }

        public boolean addToWishList(List<Book> books) {
            return false;
        }
    }

    class Library {
        /* [FILTER | SORT] functionality will be used we have to optimize the book indexing
         * since the books added will be comparatively less when compared to the requests
         * for sorting them filtering them we can perform like rehashing when the books are
         * loaded into the database. In case if there is downtime acceptable then it can
         * be ideally done during a specific time every day or two or a week which ever is
         * ideal and then rehashed again to improve the search timing */
        /* Here some considerations are to be taken into account
         *  - does the library load being made at a client side once and then filter
         *    sort and other such functionality are performed at a client side and only
         *    adding books, proceed to checkout and other features where interaction is
         *    needed from an server side the server call is initiated then we would need additional details
         *    ? If the memory efficient solutions is needed then the n pass algorithm needs to be implemented
         *    ? If the speed efficient solution is needed then we have to create separate Hashmaps for the
         *      categories by which filter operates only once and so subsequent request are faster
         *  - If the operation can be performed a server level then
         *    ? speed efficient could be set up by storing separate hash maps for the category and the requests
         *      are processed
         *    ? memory is not acceptable because at a server side many requests will come in and so the processor
         *      has to be used minimally in the case */
        /* BECAUSE OF THESE CONSIDERATIONS WE ARE DECIDING THE ONE WITH SPEED EFFICIENT ONE AS A ACCEPTABLE
         * I.E. CREATING SEPARATE HASHMAPS FOR EACH CATEGORY UNDER CONSIDERATION AND THEN REPLY SUBSEQUENT REQUESTS
         * AT A SERVER SIDE RATHER THAN PERFORMING THIS FOR EACH AND EVERY USER THERE BY SLOWING THEIR INDIVIDUAL
         * PERFORMANCE */

        List<Book> books;
        HashMap<String, List<Book>> forFilterByTitle;
        HashMap<String, List<Book>> forFilterByGenre;
        HashMap<String, List<Book>> forFilterByRating;
        HashMap<String, List<Book>> forFilterByAuthor;
        HashMap<String, List<Book>> forFilterByPrice;
        HashMap<String, List<Book>> forFilterByTags;

        /* Called when the online library is opened for the first time */
        public void init() {
            processTheHashingMechanism();
        }

        private void processTheHashingMechanism() {

        }

        public void viewBook() {

        }

        public void selectBook() {

        }

        public void sortBy() {

        }

        public void filterBy() {

        }
    }

    class OnlineLibrary extends Library {

        public OnlineLibrary() {
            super();
        }

        /* Appears only at a Online Library Level */
        public void excludePurchasedBooks(PersonalLibrary personalLibrary) {
            /* Use effecitent algorithm to remove the books from online library
             * which has been already bought under the user account */
        }
    }

    class PersonalLibrary extends Library {
        public void viewList() {

        }

        private void startReading(Book book) {

        }

        private void resumeReading(Book book) {

        }

        public void read(Book book) {

        }
    }

    /* Book Related START */
    class Book {
        String isdn;//Unique Identifier
        String name;
        String author;
        double price;
        Date dateOfPublication;
        String edition;
        List<Page> pages;
        List<Genre> genres;
        List<Tag> tags;
        List<Rating> ratings;
        List<Review> reviews;

        Bookmark bookmark;

        public Book(String isdn, String name, String author, double price, Date dateOfPublication, String edition, List<Page> pages) {
            this.isdn = isdn;
            this.name = name;
            this.author = author;
            this.price = price;
            this.dateOfPublication = dateOfPublication;
            this.edition = edition;
            this.pages = pages;
        }

        /* ALL THE ADD AND REMOVE OPERATIONS IF GOING TO BE SAVED IN THE DATABASE
         * THEN PERFORM THE OPERATION FIRST THEN REMOVE FROM THE UI */
        public boolean addGenre(Genre genre) {
            if (genre == null) return false;
            this.genres.add(genre);
            return true;
        }

        public boolean removeGenre(Genre genre) {
            return false;
        }

        public boolean removeGenres(List<Genre> genres) {
            return false;
        }

        public boolean addTag(String name) {
            if (name == null || name.isEmpty()) return false;
            this.tags.add(new Tag(name));
            return true;
        }

        public boolean removeTag(Tag tag) {
            return false;
        }

        public boolean removeTags(List<Tag> tags) {
            return false;
        }

        public boolean addRating(Rating rating) {
            if (rating == null) return false;
            this.ratings.add(rating);
            return true;
        }

        public boolean removeRating(Rating rating) {
            return false;
        }

        public boolean addReview(Review review) {
            if (review == null) return false;
            this.reviews.add(review);
            return true;
        }

        public boolean removeReview(Review review) {
            return false;
        }
    }

    class Page {
        int number;
        String content;

        public Page(int number, String content) {
            this.number = number;
            this.content = content;
        }
    }

    class Bookmark {
        int pageNumber;

        public Bookmark(int pageNumber) {
            this.pageNumber = pageNumber;
        }
    }

    /* Adventure, Mythical, Sci-Fi, Drama, Detective, Fantasy, Crime, Horror, Thriller, Comedy, Comic, Magazine, etc */
    class Genre {
        int id;
        String name;

        public Genre(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    /* User defined tags - Best Seller, Pulitzer prize winner (Awards won), etc */
    class Tag {
        long id;
        String name;

        Tag(String name) {
            createTag(name);
        }

        /* PRIVATE so that it can be used to construct the tag internally as id cannot/should not be modified by user */
        private Tag(long id, String name) {
            this.id = id;
            this.name = name;
        }

        /* Find existing tag or return a newly created tag */
        void createTag(String name) {
            this.id = fetchId(name);
            this.name = name;
        }

        /* Fetch the available ID from Database after successful save*/
        private int fetchId(String name) {
            return -1;
        }
    }

    class Rating {
        long id;
        int value;//Stars out of 5
        String reason;

        public Rating(int value, String reason) {
            this.id = fetchId(value, reason);
            this.value = value;
            this.reason = reason;
        }

        /* Fetch the available ID from Database after successful save*/
        private int fetchId(int value, String reason) {
            return -1;
        }
    }

    class Review {
        long id;
        String comments;

        public Review(String comments) {
            this.id = fetchId(comments);
            this.comments = comments;
        }

        /* Fetch the available ID from Database after successful save*/
        private int fetchId(String comments) {
            return -1;
        }
    }
    /* Book Related END */

    class ReadMode {
        Book book;
        ReaderSetting setting;
        int currentPage;

        public ReadMode(Book book, ReaderSetting setting) {
            this.book = book;
            this.setting = setting;
            this.currentPage = book.bookmark.pageNumber;
        }

        public void bookmarkHere() {

        }

        public void highlightNotes() {

        }

        public void closeBook() {

        }

        public void flipPage(boolean isForwardClicked) {
            if (isForwardClicked) {
                flipAnimation(true, Math.abs(this.currentPage + 1));
                goToPage(this.currentPage + 1);//+1
            } else {
                flipAnimation(false, Math.abs(this.currentPage - 1));
                goToPage(this.currentPage - 1);//-1
            }
        }

        public void goToPage(int pageNumber) {
            flipAnimation(this.currentPage > pageNumber, Math.abs(this.currentPage - pageNumber));
            this.currentPage = pageNumber;
        }

        /* Based on the side clicked or the page selected the animation is shown for effect */
        public void flipAnimation(boolean isForwardClicked, int numberOfPages) {

        }

    }

    /* Additional Information just for discussion
     * personalize the settings for the account level and at a reader level */
    class Setting {

    }

    class AccountSetting extends Setting {

    }

    class ReaderSetting extends Setting {

    }
}
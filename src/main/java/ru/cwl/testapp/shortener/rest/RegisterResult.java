package ru.cwl.testapp.shortener.rest;

/***
 * Response parameters in case of successful registration are as follows:
 Response
  shortUrl (shortened URL)
 Example: { shortUrl: 'http://short.com/xYswlE'}
 */
class RegisterResult {
    public RegisterResult() {
    }

    String shortUrl = "http://short.com/xYswlE";

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }
}

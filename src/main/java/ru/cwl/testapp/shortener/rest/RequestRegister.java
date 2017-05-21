package ru.cwl.testapp.shortener.rest;

/***
 * JSON object with the following parameters:
  url (mandatory, url that needs shortening)
  redirectType : 301 | 302 (not mandatory, default 302)
 */
public class RequestRegister {
    public RequestRegister() {
    }

    public String url;
    public String redirectType;




}

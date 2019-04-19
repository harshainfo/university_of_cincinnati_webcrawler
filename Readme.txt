University of Cincinnati Web Crawler App

This app was designed to extract information from professor profile sites of University of Cincinnati. Extracted information is written across 15 tables in a PostGreSQL database.

Technologies Used:
> Java language
> jsoup library
> regular expressions

How to run from Command Line Client

> Go to src folder WebCrawlApp
> Enter following to compile
	javac -cp ".;../lib/jsoup-1.11.2.jar" WebCrawlApp.java
> Enter following to run
	java -cp ".;../lib/jsoup-1.11.2.jar" WebCrawlApp
	
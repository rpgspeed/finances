# Finances
This application is a standalone springboot application that connects to YahooFinances API to download last prices of nasdaq stocks.
These stocks are stored in a database (h2 or mysql) and has a scheduled task to update the information every minut.

Steps to run application (on root folder):
1. mvn clean install
2. java -jar target/finances-0.0.1-SNAPSHOT.jar

Example companies symbols:
GRFS
CVCO

Other symbols to download are inside companylist.csv file on "/src/main/resources/static/companylist.csv"

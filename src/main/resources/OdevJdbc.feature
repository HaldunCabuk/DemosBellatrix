Feature: Odev jdbc

  Scenario: Scenaro

    Given use the following database connection
      | url      | jdbc:mysql://localhost:3306/guidersoft |
      | username | jdbc                                   |
      | password | jdbc123456                             |


    And   user create table "adress" as follows
      | id INT NOT NULL AUTO_INCREMENT |
      | first_name VARCHAR(45)         |
      | last_name VARCHAR(45)          |
      | company VARCHAR(45)            |
      | address1 VARCHAR(100)          |
      | address2 VARCHAR(100)          |
      | postcode VARCHAR(10)           |
      | country VARCHAR(45)            |
      | state VARCHAR(45)              |
      | PRIMARY KEY (id)               |

    And   user insert table "adress" the following data
      | first_name | {String-10} |
      | last_name  | {String-5}  |
      | company    | {String-10} |
      | address1   | {String-20} |
      | address2   | {String-20} |
      | postcode   | {int-6}     |
      | country    | Turkey      |
      | state      | Adana       |

    And save last insert id
    And user apply the sql "UPDATE adress SET state='Ankara'" to the last record

    When  user navigate to "https://opencart.abstracta.us/index.php?route=account/login"
    And   user login username as "deneme@deneme.com", and password as "deneme"
    Then  the text "Edit Account" should be visible
    When  user clicks the link with text "Address Book"
    And   user clicks the link with text "New Address"
    Then  the element with attribute "First Name" should be visible
    When  user fill the form with last inserted data from table "adress"
    When  user get sql "SELECT first_name, last_name FROM adress" with last insert id



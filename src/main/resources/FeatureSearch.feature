
Feature: DemosBellatrix


Scenario Outline: Search
Given user on homepage
Then  homepage should be opened
When  user search for "<searchText>"
Then  there must be <productNumber> listed product

Examples:
| searchText | productNumber |
| falcon     | 2             |
| proton     | 2             |
| heavy      | 3             |



  Scenario Outline: Search
Given user on homepage
Then  homepage should be opened
When  user search for "<searchText>"
Then  there must be <productNumber> listed product

Examples:
| searchText | productNumber |
| proton     | 2             |
| falcon     | 2             |
| heavy      | 3             |
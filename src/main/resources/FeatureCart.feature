Feature: Add to Cart

  Scenario: Add to Cart
    Given user on homepage
    When  user add the product "falcon 9" to the cart
    And   user click to Cart
    Then  the product "falcon 9" should be in Cart
Feature: FizzBuzz Game play
  Scenario Outline: Play FizzBuzz to get Fizz
    Given Create a FizzBuzz game play
    When  I play with number <number>
    Then The result is "<result>"
    Examples:
    | number | result |
    | 3      |Fizz    |
    | 5      |Buzz    |

#    Given Create a FizzBuzz game play
#    When  I play with number 5
#    Then The result is "Buzz"
#
#    Given Create a FizzBuzz game play
#    When  I play with number 15
#    Then The result is "FizzBuzz"
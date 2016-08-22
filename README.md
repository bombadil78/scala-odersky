# scala-odersky
Functional Programming Course on Coursera by Martin Odersky

# List
Immutable recursive parametrized list implementation consisting of Cons and Nil. Supports pattern matching.

Examples:

- val bc: List[String] = List("b", "c")
- val abc = "a" :: bc
- val abcdef = abc ::: List("d", "e", "f")

# Tuple
Immutable datastructure containing a fixed number of items of arbitrary type.

Examples:

- ("a", 1, Console)
- (Int, String)

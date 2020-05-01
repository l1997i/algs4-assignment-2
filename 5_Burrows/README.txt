# Assignment 5: Burrows Wheeler

* **Subimit time**: May 1, 2020 7:47 PM CST

* **Score**: 100 (Full score 100)

Correctness:  68/68 tests passed

Memory:       10/10 tests passed

Timing:       159/159 tests passed

Aggregate score: 100.00%
[Compilation: 5%, API: 5%, Spotbugs: 0%, PMD: 0%, Checkstyle: 0%, Correctness: 60%, Memory: 10%, Timing: 20%]



********************************************************************************
*  TESTING CORRECTNESS
********************************************************************************

Testing correctness of CircularSuffixArray
*-----------------------------------------------------------
Running 15 total tests.

Test 1: check index() and length() with random binary strings
  * length = 10
  * length = 100
  * length = 1000
==> passed

Test 2: check index() and length() with random uppercase strings
  * length = 10
  * length = 100
  * length = 1000
==> passed

Test 3: check index() and length() with random ASCII strings
  * length = 10
  * length = 100
  * length = 1000
==> passed

Test 4: check index() and length() with random extended ASCII strings
  * length = 10
  * length = 100
  * length = 1000
==> passed

Test 5: check index() and length() with strings from text files
  * cadabra.txt
  * amendments.txt
  * moby1.txt
  * dickens1000.txt
==> passed

Test 6: check index() and length() with strings from binary files
  * us.gif
  * CS_bricks.jpg
  * rand1K.bin
==> passed

Test 7: check index() and length() with random strings of length 0, 1, and 2
  * length = 0
  * length = 1
  * length = 2
==> passed

Test 8: check that index() throws an exception when argument is out of bounds
  * string of length 10
  * string of length 100
  * string of length 2
  * string of length 1
  * string of length 0
==> passed

Test 9: check that constructor throws an exception when argument is null
==> passed

Test 10: check that two CircularSuffixArray objects can be created at the same time
  * cadabra.txt and amendments.txt
  * amendments.txt and cadabra.txt
  * dickens1000.txt and cadabra.txt
==> passed

Test 11: check that CircularSuffixArray is immutable
  * string = "MWEWZZNBPBZUJPJMAEACKIEBSAKJZP"
  * string = "ABBAAAAAABABBBBAABAABAAABBABBB"
  * string = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
  * string = hex representation: e8 45 aa 35 1d e2 9d 19 48 b4 05 64 29 f1 2d 
==> passed

Test 12: check index() and length() with corner-case strings
  * a.txt
  * nomatch.txt
  * zebra.txt
  * alphanum.txt
==> passed

Test 13: check index() and length() with strings that are nontrivial circular
         that are nontrivial circular suffixes of themselves
  * stars.txt
  * couscous.txt
==> passed

Test 14: check index() and length() with unary strings
  * length 10 string over unary alphabet
  * length 100 string over unary alphabet
  * length 1000 string over unary alphabet
==> passed

Test 15: check index() and length() with random strings
         that are nontrivial circular suffixes of themselves
  * length 2 string over binary alphabet, repeated 2 times
  * length 2 string over binary alphabet, repeated 10 times
  * length 5 string over binary alphabet, repeated 2 times
  * length 5 string over binary alphabet, repeated 3 times
  * length 5 string over binary alphabet, repeated 5 times
  * length 7 string over uppercase alphabet, repeated 2 times
  * length 7 string over uppercase alphabet, repeated 3 times
  * length 7 string over uppercase alphabet, repeated 5 times
==> passed

Total: 15/15 tests passed!


================================================================
Testing correctness of MoveToFront
*-----------------------------------------------------------
Running 25 total tests.

Test 1a: check main() on text files
  * java MoveToFront - < abra.txt
  * java MoveToFront - < zebra.txt
  * java MoveToFront - < amendments.txt
  * java MoveToFront - < aesop.txt
==> passed

Test 1b: check main() on text files
  * java MoveToFront + < abra.txt.mtf
  * java MoveToFront + < zebra.txt.mtf
  * java MoveToFront + < amendments.txt.mtf
  * java MoveToFront + < aesop.txt.mtf
==> passed

Test 2a: check parsing of argument "-" in main() on text files
  * java MoveToFront - < abra.txt
  * java MoveToFront - < zebra.txt
  * java MoveToFront - < amendments.txt
  * java MoveToFront - < aesop.txt
==> passed

Test 2b: check parsing of argument "+" in main() on text files
  * java MoveToFront + < abra.txt.mtf
  * java MoveToFront + < zebra.txt.mtf
  * java MoveToFront + < amendments.txt.mtf
  * java MoveToFront + < aesop.txt.mtf
==> passed

Test 3a: check that main() is consistent with encode() on text files
  * abra.txt
  * zebra.txt
  * amendments.txt
  * aesop.txt
==> passed

Test 3b: check that main() is consistent with decode() on text files
  * abra.txt.mtf
  * zebra.txt.mtf
  * amendments.txt.mtf
  * aesop.txt.mtf
==> passed

Test 4a: check encode() on text files
  * abra.txt
  * zebra.txt
  * amendments.txt
  * aesop.txt
  * stars.txt
  * alphanum.txt
  * a.txt
==> passed

Test 4b: check encode() on binary files
  * us.gif
  * CS_bricks.jpg
  * rand10K.bin
==> passed

Test 4c: check encode() on random inputs
  * 10 random characters from { A } alphabet
  * 10 random characters from { A, B } alphabet
  * 10 random characters from { A, T, C, G } alphabet
  * 10 random characters from uppercase letter alphabet
  * 1000 random characters from { A } alphabet
  * 1000 random characters from { A, B } alphabet
  * 1000 random characters from { A, T, C, G } alphabet
  * 1000 random characters from uppercase letter alphabet
==> passed

Test 4d: check encode() on more random inputs
  * 1000 random characters from ASCII alphabet 
  * 1000 random characters from extended ASCII alphabet
  * 1000 random characters from extended ASCII alphabet (excluding 0x00)
  * 1000 random characters from extended ASCII alphabet (excluding 0xFF)
==> passed

Test 5a: check decode() on move-to-front-encoded text files
  * abra.txt.mtf
  * zebra.txt.mtf
  * amendments.txt.mtf
  * aesop.txt.mtf
  * stars.txt.mtf
  * alphanum.txt.mtf
  * a.txt.mtf
==> passed

Test 5b: check decode() on move-to-front encoded binary files
  * us.gif.mtf
  * CS_bricks.jpg.mtf
  * rand10K.bin.mtf
==> passed

Test 5c: check decode() on random inputs
  * 10 random characters from { A } alphabet
  * 10 random characters from { A, B } alphabet
  * 10 random characters from { A, T, C, G } alphabet
  * 10 random characters from uppercase letter alphabet
  * 1000 random characters from { A } alphabet
  * 1000 random characters from { A, B } alphabet
  * 1000 random characters from { A, T, C, G } alphabet
  * 1000 random characters from uppercase letter alphabet
==> passed

Test 5d: check decode() on more random inputs
  * 1000 random characters from ASCII alphabet 
  * 1000 random characters from extended ASCII alphabet
  * 1000 random characters from extended ASCII alphabet (excluding 0x00)
  * 1000 random characters from extended ASCII alphabet (excluding 0xFF)
==> passed

Test 5e: check decode() on random inputs
         that were encoded with move-to-front
  * 10 random characters from { A } alphabet
  * 10 random characters from { A, B } alphabet
  * 10 random characters from { A, T, C, G } alphabet
  * 10 random characters from uppercase letter alphabet
  * 1000 random characters from { A } alphabet
  * 1000 random characters from { A, B } alphabet
  * 1000 random characters from { A, T, C, G } alphabet
  * 1000 random characters from uppercase letter alphabet
==> passed

Test 5f: check decode() on more random inputs
         that were encoded with move-to-front
  * 1000 random characters from ASCII alphabet 
  * 1000 random characters from extended ASCII alphabet
  * 1000 random characters from extended ASCII alphabet (excluding 0x00)
  * 1000 random characters from extended ASCII alphabet (excluding 0xFF)
==> passed

Test 6a: check whether decode(encode()) = original on text files
  * abra.txt
  * zebra.txt
  * amendments.txt
  * aesop.txt
  * stars.txt
  * alphanum.txt
  * a.txt
==> passed

Test 6b: check whether decode(encode()) = original on binary files
  * us.gif
  * CS_bricks.jpg
  * rand10K.bin
==> passed

Test 6c: check that decode(encode()) = original on random inputs
  * 10 random characters from { A } alphabet
  * 10 random characters from { A, B } alphabet
  * 10 random characters from { A, T, C, G } alphabet
  * 10 random characters from uppercase letter alphabet
  * 100 random characters from { A } alphabet
  * 1000 random characters from { A, B } alphabet
  * 1000 random characters from { A, T, C, G } alphabet
  * 1000 random characters from uppercase letter alphabet
==> passed

Test 6d: check that decode(encode()) = original on random inputs
  * 1000 random characters from ASCII alphabet 
  * 1000 random characters from extended ASCII alphabet
  * 1000 random characters from extended ASCII alphabet (excluding 0x00)
  * 1000 random characters from extended ASCII alphabet (excluding 0xFF)
==> passed

Test 7a: check that encode() calls either close() or flush()
  * amendments.txt
  * aesop.txt
==> passed

Test 7b: check that decode() calls either close() or flush()
  * amendments.txt.mtf
  * aesop.txt.mtf
==> passed

Test 8a: check encode() on large files
  * rand100K.bin
  * world192.txt
==> passed

Test 8b: check decode() on large files
  * rand100K.bin.mtf
  * world192.txt.mtf
==> passed

Test 8c: check whether decode(encode()) = original on large files
  * rand100K.bin
  * world192.txt
==> passed


Total: 25/25 tests passed!


================================================================
********************************************************************************
*  TESTING CORRECTNESS (substituting reference CircularSuffixArray)
********************************************************************************

Testing correctness of BurrowsWheeler
*-----------------------------------------------------------
Running 28 total tests.

Test 1a: check main() on text files
  * java BurrowsWheeler - < abra.txt
  * java BurrowsWheeler - < zebra.txt
  * java BurrowsWheeler - < cadabra.txt
  * java BurrowsWheeler - < amendments.txt
==> passed

Test 1b: check main() on text files
  * java BurrowsWheeler + < abra.txt.bwt
  * java BurrowsWheeler + < zebra.txt.bwt
  * java BurrowsWheeler + < cadabra.txt.bwt
  * java BurrowsWheeler + < amendments.txt.bwt
==> passed

Test 2a: check parsing of argument "-" in main() on text files
  * java BurrowsWheeler - < abra.txt
  * java BurrowsWheeler - < zebra.txt
  * java BurrowsWheeler - < cadabra.txt
  * java BurrowsWheeler - < amendments.txt
==> passed

Test 2b: check parsing of argument "+" in main() on text files
  * java BurrowsWheeler + < abra.txt.bwt
  * java BurrowsWheeler + < zebra.txt.bwt
  * java BurrowsWheeler + < cadabra.txt.bwt
  * java BurrowsWheeler + < amendments.txt.bwt
==> passed

Test 3a: check that main() is consistent with transform() on text files
  * abra.txt
  * zebra.txt
  * cadabra.txt
  * amendments.txt
==> passed

Test 3b: check that main() is consistent with inverseTransform() on text files
  * abra.txt.bwt
  * zebra.txt.bwt
  * cadabra.txt.bwt
  * amendments.txt.bwt
==> passed

Test 4a: check transform() on text files
  * abra.txt
  * zebra.txt
  * cadabra.txt
  * amendments.txt
==> passed

Test 4b: check transform() on corner-case text files
  * alphanum.txt
  * a.txt
==> passed

Test 4c: check transform() on binary files
  * us.gif
  * CS_bricks.jpg
  * rand10K.bin
==> passed

Test 4d: check transform() on random inputs
  * 10 random characters from binary alphabet
  * 10 random characters from DNA alphabet
  * 10 random characters from uppercase alphabet
  * 1000 random characters from binary alphabet
  * 1000 random characters from DNA alphabet
  * 1000 random characters from uppercase alphabet
==> passed

Test 4e: check transform() on more random inputs
  * 1000 random characters from ASCII alphabet 
  * 1000 random characters from extended ASCII alphabet
  * 1000 random characters from extended ASCII alphabet (excluding 0x00)
  * 1000 random characters from extended ASCII alphabet (excluding 0xFF)
==> passed

Test 4f: check tranform() on random inputs that are circular
         shifts of themselves
  * 5 random strings from unary alphabet
  * 5 random strings from binary alphabet
  * 5 random strings from DNA alphabet
  * 5 random strings from uppercase alphabet
==> passed

Test 5a: check inverseTransform() on text files
  * abra.txt.bwt
  * zebra.txt.bwt
  * cadabra.txt.bwt
  * amendments.txt.bwt
==> passed

Test 5b: check inverseTransform() on corner-case text files
  * alphanum.txt.bwt
  * a.txt.bwt
  * stars.txt.bwt
  * couscous.txt.bwt
==> passed

Test 5c: check inverseTransform() on binary files
  * us.gif.bwt
  * CS_bricks.jpg.bwt
  * rand10K.bin.bwt
==> passed

Test 5d: check inverseTransform() of transform() on random inputs
  * 10 random characters from unary alphabet
  * 10 random characters from binary alphabet
  * 10 random characters from DNA alphabet
  * 10 random characters from uppercase alphabet
  * 100 random characters from unary alphabet
  * 1000 random characters from binary alphabet
  * 1000 random characters from DNA alphabet
  * 1000 random characters from uppercase alphabet
==> passed

Test 5e: check inverseTransform() of transform() on more random inputs
  * 1000 random characters from ASCII alphabet 
  * 1000 random characters from extended ASCII alphabet
  * 1000 random characters from extended ASCII alphabet (excluding 0x00)
  * 1000 random characters from extended ASCII alphabet (excluding 0xFF)
==> passed

Test 6a: check that inverseTransform(transform()) = original on text files
  * abra.txt
  * zebra.txt
  * cadabra.txt
  * amendments.txt
==> passed

Test 6b: check that inverseTransform(transform()) = original on corner-case text files
  * alphanum.txt
  * a.txt
  * stars.txt
  * couscous.txt
==> passed

Test 6c: check that inverseTransform(transform()) = original on binary files
  * us.gif
  * CS_bricks.jpg
  * rand10K.bin
==> passed

Test 6d: check that inverseTransform(tranform()) = original on random inputs
  * 10 random characters from binary alphabet
  * 10 random characters from DNA alphabet
  * 10 random characters from uppercase alphabet
  * 1000 random characters from binary alphabet
  * 1000 random characters from DNA alphabet
  * 1000 random characters from uppercase alphabet
==> passed

Test 6e: check that inverseTransform(tranform()) = original on random inputs
  * 1000 random characters from ASCII alphabet 
  * 1000 random characters from extended ASCII alphabet
  * 1000 random characters from extended ASCII alphabet (excluding 0x00)
  * 1000 random characters from extended ASCII alphabet (excluding 0xFF)
==> passed

Test 6f: check that inverseTransform(tranform()) = original
         on random inputs that are circular shifts of themselves
  * random strings from unary alphabet
  * random strings from binary alphabet
  * random strings from DNA alphabet
  * random strings from uppercase alphabet
==> passed

Test 7a: check that transform() calls either close() or flush()
  * amendments.txt
  * aesop.txt
==> passed

Test 7b: check that inverseTransform() calls either close() or flush()
  * amendments.txt.bwt
  * aesop.txt.bwt
==> passed

Test 8a: check transform() on large files
  * rand100K.bin
  * world192.txt
==> passed

Test 8b: check inverseTransform() on large files
  * rand100K.bin.bwt
  * world192.txt.bwt
==> passed

Test 8c: check that inverseTransform(transform()) = original on large files
  * rand100K.bin
  * world192.txt
==> passed


Total: 28/28 tests passed!


================================================================
********************************************************************************
*  MEMORY
********************************************************************************

Analyzing memory of CircularSuffixArray
*-----------------------------------------------------------
Running 10 total tests.

Memory usage of a CircularSuffixArray for a random string of length n.
Maximum allowed memory is 64n + 128.

                 n        bytes
-------------------------------
=> passed       16          904
=> passed       32         1688
=> passed       64         3256
=> passed      128         6392
=> passed      256        12664
=> passed      512        25208
=> passed     1024        50296
=> passed     2048       100472
=> passed     4096       200824
=> passed     8192       401528
==> 10/10 tests passed

Total: 10/10 tests passed!

Estimated student memory (bytes) = 49.00 n + 120.00   (R^2 = 1.000)

================================================================



********************************************************************************
*  TIMING
********************************************************************************

Timing CircularSuffixArray
*-----------------------------------------------------------
Running 26 total tests.

Tests  1-13: time to create a circular suffix array for the first
             n character of dickens.txt and call index(i) for each i

            [ max allowed time = 10 seconds and <= 12x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.01       0.00      11.58
=> passed     2000       0.00       0.00       3.86
=> passed     4000       0.01       0.00       5.96
=> passed     8000       0.01       0.00       3.67
=> passed    16000       0.01       0.00       2.10
=> passed    32000       0.02       0.01       3.32
=> passed    64000       0.05       0.01       5.98
=> passed   128000       0.10       0.02       5.90
=> passed   256000       0.15       0.02       6.89
=> passed   512000       0.36       0.05       6.63
=> passed  1024000       0.83       0.11       7.57
=> passed  2048000       1.98       0.29       6.86
=> passed  4096000       4.74       0.78       6.04

Estimated running time (using last 6 measurements)
    = 1.23e-07 * n^1.14  (R^2 = 0.99)


Tests 14-26: time to create circular suffix array for n random ASCII characters
            and call index(i) for each i

            [ max allowed time = 10 seconds and <= 20x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       6.11
=> passed     2000       0.00       0.00       4.08
=> passed     4000       0.00       0.00       4.36
=> passed     8000       0.00       0.00       5.39
=> passed    16000       0.01       0.00       6.23
=> passed    32000       0.01       0.00       9.80
=> passed    64000       0.04       0.00      14.54
=> passed   128000       0.07       0.00      16.94
=> passed   256000       0.26       0.02      15.52
=> passed   512000       0.42       0.03      13.18
=> passed  1024000       1.18       0.18       6.67
=> passed  2048000       2.23       0.13      17.51
=> passed  4096000       5.26       0.39      13.32

Estimated running time (using last 6 measurements)
    = 6.69e-08 * n^1.20  (R^2 = 0.99)


Total: 26/26 tests passed!


================================================================



********************************************************************************
*  TIMING
********************************************************************************

Timing MoveToFront
*-----------------------------------------------------------
Running 38 total tests.

Test 1: count calls to methods in BinaryStdOut from encode()
  * abra.txt
  * amendments.txt
==> passed

Test 2: count calls to methods in BinaryStdOut from decode()
  * abra.txt.mtf
  * amendments.txt.mtf
==> passed

Tests  3-12: Timing encode() with first n character of dickens.txt
             [ max allowed time = 2 seconds and <= 4x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       2.35
=> passed     2000       0.01       0.00       3.34
=> passed     4000       0.01       0.00       3.60
=> passed     8000       0.02       0.01       3.42
=> passed    16000       0.04       0.01       3.37
=> passed    32000       0.11       0.03       3.32
=> passed    64000       0.16       0.05       3.29
=> passed   128000       0.38       0.10       3.91
=> passed   256000       0.63       0.20       3.21

Estimated running time (using last 6 measurements)
     = 3.55e-06 * n^0.98  (R^2 = 0.99)


Tests  13-20: Timing encode() with first n character of abab.txt
             [ max allowed time = 2 seconds and <= 4x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       3.11
=> passed     2000       0.00       0.00       3.10
=> passed     4000       0.01       0.00       3.06
=> passed     8000       0.02       0.01       3.06
=> passed    16000       0.03       0.01       3.05
=> passed    32000       0.09       0.03       3.02
=> passed    64000       0.17       0.05       3.21
=> passed   128000       0.27       0.09       3.03
=> passed   256000       0.64       0.18       3.50

Estimated running time (using last 6 measurements)
     = 1.83e-06 * n^1.03  (R^2 = 0.99)


Tests 21-29: Timing decode() with first n character of dickens.txt
             [ max allowed time = 2 seconds and <= 4x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       0.99
=> passed     2000       0.00       0.00       0.75
=> passed     4000       0.00       0.00       0.97
=> passed     8000       0.01       0.01       0.99
=> passed    16000       0.01       0.01       0.83
=> passed    32000       0.02       0.02       0.99
=> passed    64000       0.05       0.05       0.96
=> passed   128000       0.11       0.12       0.92
=> passed   256000       0.23       0.19       1.20

Estimated running time (using last 6 measurements)
     = 4.85e-07 * n^1.05  (R^2 = 1.00)


Tests 30-38: Timing decode() with first n character of abab.txt
             [ max allowed time = 2 seconds and <= 4x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       1.01
=> passed     2000       0.00       0.00       1.02
=> passed     4000       0.00       0.00       1.09
=> passed     8000       0.01       0.01       1.00
=> passed    16000       0.01       0.01       1.16
=> passed    32000       0.02       0.02       0.98
=> passed    64000       0.05       0.05       0.91
=> passed   128000       0.09       0.09       1.01
=> passed   256000       0.18       0.18       1.01

Estimated running time (using last 6 measurements)
     = 9.08e-07 * n^0.98  (R^2 = 1.00)


Total: 38/38 tests passed!


================================================================



********************************************************************************
*  TIMING (substituting reference CircularSuffixArray)
********************************************************************************

Timing BurrowsWheeler
*-----------------------------------------------------------
Running 95 total tests.

Test 1: count calls to methods in CircularSuffixArray from transform()
  * abra.txt
  * amendments.txt
==> passed

Test 2: count calls to methods in CircularSuffixArray from inverseTransform()
  * abra.txt.bwt
  * amendments.txt.bwt
==> passed

Test 3: count calls to methods in BinaryStdOut from transform()
  * abra.txt
  * amendments.txt
==> passed

Test 4: count calls to methods in BinaryStdOut from inverseTransform()
  * abra.txt.bwt
  * amendments.txt.bwt
==> passed

Tests  5-17: timing transform() with first n character of dickens.txt
             [ max allowed time = 2 seconds and <= 8x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       0.11
=> passed     2000       0.00       0.00       0.95
=> passed     4000       0.00       0.00       0.98
=> passed     8000       0.00       0.00       0.94
=> passed    16000       0.01       0.01       0.92
=> passed    32000       0.01       0.01       0.85
=> passed    64000       0.02       0.02       0.96
=> passed   128000       0.02       0.02       0.96
=> passed   256000       0.04       0.04       1.16
=> passed   512000       0.09       0.08       1.08
=> passed  1024000       0.19       0.19       0.96
=> passed  2048000       0.46       0.38       1.22
=> passed  4096000       1.06       0.99       1.08

Estimated running time as a function of n (using last 6 measurements)
    = 2.79e-08 * n^1.14  (R^2 = 1.00)


Tests 18-30: timing transform() with first n character of random.bin
             [ max allowed time = 2 seconds and <= 8x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       1.25
=> passed     2000       0.00       0.00       1.25
=> passed     4000       0.00       0.00       1.12
=> passed     8000       0.00       0.00       1.11
=> passed    16000       0.00       0.00       1.27
=> passed    32000       0.01       0.00       1.14
=> passed    64000       0.01       0.01       1.12
=> passed   128000       0.02       0.11       0.17
=> passed   256000       0.04       0.04       0.96
=> passed   512000       0.09       0.09       0.94
=> passed  1024000       0.19       0.18       1.05
=> passed  2048000       0.52       0.39       1.36
=> passed  4096000       1.40       1.16       1.21

Estimated running time as a function of n (using last 6 measurements)
    = 7.70e-09 * n^1.24  (R^2 = 1.00)


Tests 31-43: timing transform() with first n character of abab.txt
             [ max allowed time = 2 seconds and <= 8x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       0.82
=> passed     2000       0.00       0.00       1.04
=> passed     4000       0.00       0.00       1.06
=> passed     8000       0.00       0.00       1.07
=> passed    16000       0.00       0.00       1.04
=> passed    32000       0.00       0.00       1.05
=> passed    64000       0.00       0.00       1.17
=> passed   128000       0.00       0.00       1.20
=> passed   256000       0.01       0.01       1.21
=> passed   512000       0.01       0.01       1.27
=> passed  1024000       0.03       0.02       1.22
=> passed  2048000       0.05       0.05       1.17
=> passed  4096000       0.14       0.09       1.49

Estimated running time as a function of n (using last 6 measurements)
    = 1.41e-08 * n^1.05  (R^2 = 1.00)


Tests 44-56: timing inverseTransform() with first n character of dickens.txt
             [ max allowed time = 2 seconds and <= 8x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       0.73
=> passed     2000       0.00       0.00       0.56
=> passed     4000       0.00       0.00       0.52
=> passed     8000       0.00       0.00       0.53
=> passed    16000       0.00       0.00       0.50
=> passed    32000       0.00       0.00       0.98
=> passed    64000       0.00       0.00       0.69
=> passed   128000       0.00       0.01       0.69
=> passed   256000       0.01       0.01       1.14
=> passed   512000       0.02       0.02       1.05
=> passed  1024000       0.07       0.07       0.93
=> passed  2048000       0.21       0.18       1.12
=> passed  4096000       0.61       0.49       1.24

Estimated running time as a function of n (using last 6 measurements)
    = 8.62e-11 * n^1.48  (R^2 = 0.99)


Tests 57-69: timing inverseTransform() with first n character of random.bin
             [ max allowed time = 2 seconds and <= 8x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1024       0.00       0.00       0.93
=> passed     2048       0.00       0.00       0.99
=> passed     4096       0.00       0.00       0.75
=> passed     8192       0.00       0.00       0.96
=> passed    16384       0.00       0.00       1.02
=> passed    32768       0.00       0.00       0.99
=> passed    65536       0.00       0.00       1.41
=> passed   131072       0.00       0.01       0.78
=> passed   262144       0.01       0.01       0.96
=> passed   524288       0.02       0.03       0.64
=> passed  1048576       0.12       0.12       1.05
=> passed  2097152       0.26       0.25       1.02
=> passed  4194304       0.75       0.75       1.00

Estimated running time as a function of n (using last 6 measurements)
    = 3.65e-11 * n^1.56  (R^2 = 0.98)


Tests 70-82: timing inverseTransform() with first n character of abab.txt
             [ max allowed time = 2 seconds and <= 8x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1000       0.00       0.00       0.91
=> passed     2000       0.00       0.00       0.81
=> passed     4000       0.00       0.00       0.99
=> passed     8000       0.00       0.00       0.97
=> passed    16000       0.00       0.00       0.93
=> passed    32000       0.00       0.00       1.02
=> passed    64000       0.00       0.00       1.01
=> passed   128000       0.00       0.00       1.50
=> passed   256000       0.01       0.01       0.87
=> passed   512000       0.01       0.01       0.90
=> passed  1024000       0.03       0.02       1.12
=> passed  2048000       0.04       0.05       0.94
=> passed  4096000       0.08       0.08       1.00

Estimated running time as a function of n (using last 6 measurements)
    = 5.07e-08 * n^0.94  (R^2 = 0.99)


Tests 83-95: timing inverseTransform() with first n character of cyclic.bin
             [ max allowed time = 2 seconds and <= 8x reference ]

                 n    student  reference      ratio
---------------------------------------------------
=> passed     1024       0.00       0.00       0.91
=> passed     2048       0.00       0.00       0.96
=> passed     4096       0.00       0.00       1.00
=> passed     8192       0.00       0.00       1.01
=> passed    16384       0.00       0.00       0.99
=> passed    32768       0.00       0.00       1.02
=> passed    65536       0.00       0.00       0.99
=> passed   131072       0.00       0.00       0.95
=> passed   262144       0.01       0.01       0.88
=> passed   524288       0.02       0.02       1.03
=> passed  1048576       0.05       0.04       1.10
=> passed  2097152       0.08       0.08       1.03
=> passed  4194304       0.19       0.18       1.04

Estimated running time as a function of n (using last 6 measurements)
    = 2.77e-09 * n^1.19  (R^2 = 1.00)


Total: 95/95 tests passed!


================================================================

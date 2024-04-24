Test list questions
- It does not make any difference whether we use a LinkedList or an ArrayList because they are both children of the
List interface and thus have the same methods
- what does the list.remove() method do - it removes the element at the ith element of the list - line 97 and 104
of TestList.java
- The list.remove(Integer.valueOf(5)) on line 104 removes the element at the 5th index

Test Iterator questions
- It does not make any difference whether we use a LinkedList or an ArrayList because they are both children of the
 List interface and thus have the same methods
- If list.remove(Integer.valeOf(77)) is used instead of i.remove() the test will not pass because
list.remove(Integer.valeOf(77)) removes the value at index 77 rather than the element that is 77

 Test Performance questions
 -The linked list performs better i.e. runs faster for the AddRemove tests. The arraylist performs better for the
 ListAccess methods. This is because LinkedLists are optimized on insertion and deletion and arrays are optimized
 on access, this is due to arrays having indexed elements and a static size
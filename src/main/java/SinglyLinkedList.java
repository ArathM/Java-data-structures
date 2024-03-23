import java.security.InvalidParameterException;

//Class that implements the structure of a linked list as well as some
//algorithms.
//Linked lists are structures not stored in continous locationa nd can be 
//of dynamic size
public class SinglyLinkedList {

    //Defining the internal nodes of the
    //linked list in its own private class.
    private ListNode head;

    //A list node consists of the data and the pointer to the next element,
    //the next element is initialized as null before being linked.
    private static class ListNode {
        private int data;
        private ListNode next;

        public ListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    //Defining the nodes of the linked list, and linking each of them
    public SinglyLinkedList(ListNode[] nodes,boolean loop, int loop_position){

        head = nodes[0];
        ListNode current = head;
        for (int i=1;i<nodes.length;i++){
            current.next = nodes[i];
            current = nodes[i];
        }
        if (loop) {
            current.next = nodes[loop_position];
        }
    }

    public static void printlist(SinglyLinkedList sll) {
        ListNode current_node = sll.head;
        while (current_node != null) {
            System.out.println(String.valueOf(current_node.data));
            current_node = current_node.next;
        }
    }

    public static int get_length(SinglyLinkedList sll) {
        ListNode current_node = sll.head;
        int count=0;
        while (current_node != null) {
            count++;
            current_node = current_node.next;
        }
        System.out.println("The length of the linked list is " + String.valueOf(count));
        return count;
    }

    public static void main(String[] args) {
        ListNode[] node_array = new ListNode[5];
        node_array[0] = new ListNode(10);
        node_array[1] = new ListNode(1);
        node_array[2] = new ListNode(8);
        node_array[3] = new ListNode(11);
        node_array[4] = new ListNode(8);

        ListNode[] sorted_array = new ListNode[5];
        sorted_array[0] = new ListNode(1);
        sorted_array[1] = new ListNode(1);
        sorted_array[2] = new ListNode(2);
        sorted_array[3] = new ListNode(3);
        sorted_array[4] = new ListNode(3);

        ListNode[] looped_array = new ListNode[5];
        looped_array[0] = new ListNode(2);
        looped_array[1] = new ListNode(1);
        looped_array[2] = new ListNode(2);
        looped_array[3] = new ListNode(3);
        looped_array[4] = new ListNode(4);

        SinglyLinkedList linked_list = new SinglyLinkedList(node_array, false, 0);
        SinglyLinkedList sorted_list = new SinglyLinkedList(sorted_array, false, 0);
        SinglyLinkedList looped_list = new SinglyLinkedList(looped_array, true, 2);

        printlist(linked_list);
        insert_node(linked_list, 4,2);
        insert_node(linked_list, 9,1);
        int list_len = get_length(linked_list);
        insert_node(linked_list, 5,list_len);
        printlist(linked_list);
        delete_node(linked_list, 2);
        delete_node(linked_list, list_len);
        delete_node(linked_list, 1);
        printlist(linked_list);
        boolean contains_element = search_list(linked_list, 4);
        System.out.println("Contains element " + contains_element);
        reverse_list(linked_list);
        printlist(linked_list);
        find_nth_node_from_end(linked_list, 2);
        find_nth_node_from_end_pointers(linked_list, 2);
        insert_node(linked_list, 4,2);
        printlist(sorted_list);
        remove_duplicates(sorted_list);
        printlist(sorted_list);
        ListNode new_node = new ListNode(4);
        insert_node_sorted(sorted_list,new_node);
        printlist(sorted_list);
        remove_from_key(sorted_list, 3);
        printlist(sorted_list);
        detect_loop(looped_list);
        detect_beginning_of_loop(looped_list);
        detect_beginning_of_loop(sorted_list);
        remove_loop(looped_list);
        printlist(looped_list);
    }

    //Insert node at specified position of linked list
    public static void insert_node(SinglyLinkedList sll, int value, int position) {

        int listlen = get_length(sll);
        if (position > listlen) {
            throw new InvalidParameterException();
        }

        //Inserting at beginning of list
        if (position==1) {
            ListNode new_node = new ListNode(value);
            new_node.next = sll.head;
            sll.head = new_node;
            return;
        }

        //At some other position

        else if (1 < position  && position < listlen) {
            int position_count = 1;
            ListNode current_node = sll.head;
            ListNode last_node = current_node;
    
            //Keeping track of both the last and the next nodes
            //to update their pointers
            while (position_count < position) {
                last_node = current_node;
                current_node = current_node.next;
                position_count++;
            }

            ListNode new_node = new ListNode(value);
            last_node.next = new_node;
            new_node.next = current_node;
            return;
        }

        //At the end of the list
        else if (position == listlen) {
            int position_count = 1;
            ListNode current_node = sll.head;
    
            //Going through the linked list to append at the end
            while (position_count < listlen) {
                current_node = current_node.next;
                position_count++;
            }

            ListNode new_node = new ListNode(value);
            current_node.next = new_node;
            return;

        }
    }

    public static void delete_node(SinglyLinkedList sll, int position) {

        int list_len = get_length(sll);

        if (position > list_len) {
            throw new InvalidParameterException();
        }

        //Beginning of list
        if (position==1) {
            ListNode temp = sll.head;
            sll.head = sll.head.next;
            temp.next = null;
        }

        //Middle of list
        else if (1 < position && position < list_len) {
            int position_count = 1;
            ListNode current_node = sll.head;
            ListNode last_node = current_node;

            //Keeping track of both the last and the next nodes
            //to update their pointers
            while (position_count < position) {
                last_node = current_node;
                current_node = current_node.next;
                position_count++;
            }

            last_node.next = current_node.next;
            current_node.next = null;
            return;
        }

        //End of list
        else {
            int position_count = 1;
            ListNode current_node = sll.head;
            ListNode last_node = current_node;

            //Keeping track of both the last and the next nodes
            //to update their pointers
            while (position_count < list_len) {
                last_node = current_node;
                current_node = current_node.next;
                position_count++;
            }

            last_node.next = null;
            return;
        }
    }

    //Search whether or not a certain value is in the list
    public static boolean search_list(SinglyLinkedList sll, int value) {

        ListNode current_node = sll.head;
        while (current_node!=null) {
            if (current_node.data == value) {
                return true;
            }
            current_node = current_node.next; 
        }
        System.out.print("Value " + String.valueOf(value) + "not found in list. ");
        return false;
    }

    //Reverses the order of the linking for a node by changing the node
    //it points to
    public static ListNode reverse_node(ListNode node,boolean is_head) {
        //Nothing to reverse if we're at the end of the list
        if (is_head) {
            node.next=null;
            return node;
        }

        ListNode current = node.next;
        current.next = node;
        node.next = null;

        return current;
    }

    //Reverses the whole list
    public static SinglyLinkedList reverse_list(SinglyLinkedList sll) {
        ListNode current_node = sll.head;
        ListNode previous_node = null;
        ListNode next_node = null;

        while (current_node != null) {
            next_node = current_node.next;
            current_node.next = previous_node;
            previous_node = current_node;
            current_node = next_node;
        }
        sll.head = previous_node;
        return sll;
    }

    //Finds the nth node from the end of the list (the first is the end itself)
    public static void find_nth_node_from_end(SinglyLinkedList sll, int n) {
        int list_len = get_length(sll);
        ListNode current_node = sll.head;

        int counter = 0;
        while (counter < list_len - n) {
            current_node = current_node.next;
            counter++;
        }

        System.out.println("The " + String.valueOf(n) + "th node from the end is " + String.valueOf(current_node.data));
    }

    //Finds the nth node from the end of the list using pointer references without knowing the length
    //by using a reference that goes n steps ahead of the main pointer, and then both go until the reference reaches
    //the end of the list. Works because the main pointer is always n steps behind the reference, so when the reference
    //reaches the end, the main pointer is at the n-th node from the end.
    public static void find_nth_node_from_end_pointers(SinglyLinkedList sll, int n) {
        ListNode main_pointer = sll.head;
        ListNode ref_pointer = sll.head;
        int count=0;
        while (count < n) {
            ref_pointer = ref_pointer.next;
            count++;
        }
        while (ref_pointer != null) {
            main_pointer = main_pointer.next;
            ref_pointer = ref_pointer.next;
        }

        System.out.println("The " + String.valueOf(n) + "th node from the end is " + String.valueOf(main_pointer.data));
    }

    //Removes duplicates from a sorted list
    public static void remove_duplicates(SinglyLinkedList sll) {
        ListNode current_node = sll.head;
        //Checks that neither the current node nor the next node are null,
        //if they are, it means we've reached the end of the sorted list
        while (current_node != null && current_node.next != null) {
            if (current_node.data == current_node.next.data) {
                current_node.next = current_node.next.next;
            }
            else {
                current_node = current_node.next;
            }
        }
    }

    //Inserts a node in a sorted list
    private static void insert_node_sorted(SinglyLinkedList sll, ListNode new_node) {
        ListNode current = sll.head;
        ListNode temp = null;

        while (current != null && current.data < new_node.data) {
            temp = current;
            current = current.next;
        }
        new_node.next = current;
        temp.next = new_node;
    }

    //Removes from key by having two pointers and skipping the one that matches the key
    public static void remove_from_key(SinglyLinkedList sll, int key) {
        ListNode current = sll.head;
        ListNode temp = null;

        while (current!=null && current.data != key) {
            temp = current;
            current = current.next;
        }
        if (current == null) {
            return;
        }
        temp.next = current.next;

    }

    //Detects a loop in a list by using two pointers moving at different
    //speeds, if they meet at some point, it means there's a loop
    public static void detect_loop(SinglyLinkedList sll) {
        ListNode slow_pointer = sll.head;
        ListNode fast_pointer = sll.head;
        while (fast_pointer != null && fast_pointer.next != null) {
            slow_pointer = slow_pointer.next;
            fast_pointer = fast_pointer.next.next;
            if (slow_pointer == fast_pointer) {
                System.out.println("Loop detected");
                return;
            }
        }
    }

    //Return the node where the loop begins using the Floyd's cycle detection algorithm,
    //which works because if there is a cycle, the two pointers will eventually meet at some point
    public static void detect_beginning_of_loop(SinglyLinkedList sll) {

        ListNode slow_pointer = sll.head;
        ListNode fast_pointer = sll.head;
        //First detected cycle
        while (fast_pointer != null && fast_pointer.next != null) {
            slow_pointer = slow_pointer.next;
            fast_pointer = fast_pointer.next.next;
            //Once a loop is detected, the slow pointer goes back
            //until the beginning of the loop is found
            if (slow_pointer == fast_pointer) {
                ListNode temp = sll.head;
                while (temp != slow_pointer) {
                    temp = temp.next;
                    slow_pointer = slow_pointer.next;
                }
                System.out.println("The loop begins at " + String.valueOf(slow_pointer.data));
                return;
            }
        }
        System.out.println("There is no loop, only suffering");
    }

    //Removes a loop from a list by changing the next pointer of the last node
    public static void remove_loop(SinglyLinkedList sll) {
        ListNode slow_pointer = sll.head;
        ListNode fast_pointer = sll.head;
        while (fast_pointer!=null && fast_pointer.next!=null) {
            slow_pointer = slow_pointer.next;
            fast_pointer = fast_pointer.next.next;
            //Instead of finding the beginning of the loop, changes the next pointer
            //of the last node to the beginning of the loop
            if (slow_pointer == fast_pointer) {
                ListNode temp = sll.head;
                //Looks one step ahead to modify the last pointer before beginning
                while (slow_pointer.next != temp.next) {
                    slow_pointer = slow_pointer.next;
                    temp = temp.next;
                }
                slow_pointer.next = null;
                return;
            }
            
        }
    }

    public static void merge_sorted_lists(SinglyLinkedList sll1, SinglyLinkedList sll2) {
    }

}

public class Collections {
    static class Node{
            String data;
            Node next;
            Node prev;
            Node(String d){
                data = d;
                next = null;
                prev = null;
            }
        }
        Node head;
        public Collections(){
            head = null;
        }

        public void add(String d){
            Node n = new Node(d);
            if(head==null)
            {
                head=n;
                n.next = null;
                n.prev = null;
            }
            else{
                Node temp = head;
                while(temp.next!=null)
                    temp = temp.next;
                temp.next = n;
                n.prev = temp;
                n.next = null;
            }

        }
        public void delete(String d){
            Node temp = head;
            if(head.data.equals(d)){
                head = temp.next;
                return;
            }
            while(temp.next!=null){
                if(temp.next.data.equals(d)){
                    temp.next = temp.next.next;
                    return;
                }
                temp = temp.next;
            }
        }
        public void printList() {
            Node temp = head;
            while (temp != null) {
                if (temp.data.contains(".")) {
                    System.out.println(temp.data.substring(0, temp.data.lastIndexOf(".")));
                }
                else {
                    System.out.println(temp.data);
                }
                temp = temp.next;
            }
        }

        public boolean search(String d){
            int i = 1;
            boolean flag = false;
            Node current = head;
            if(head == null) {
                return false;
            }
            while(current != null) {
                if(current.data.equals(d)) {
                    flag = true;
                    break;
                }
                current = current.next;
                i++;
            }
            return flag;
        }
        public boolean isEmpty(){
            return head == null;
        }
}

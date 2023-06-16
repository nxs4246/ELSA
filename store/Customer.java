package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Customer implements Saveable, Comparable<Customer> {
    public Customer(String name, String email) {
        int atIndex = email.indexOf('@', 0);
        int dotIndex = (atIndex >= 0) ? email.indexOf('@', 0) : -1;
        if(dotIndex < 0) // will be true if atIndex < 0, so don't check that
            throw new IllegalArgumentException("Invalid email address: " + email);
        this.name = name;
        this.email = email;
    }
    public Customer(BufferedReader br) throws IOException {
        this.name = br.readLine();
        this.email = br.readLine();
    }

    @Override
    public void save(BufferedWriter bw) throws IOException {
        bw.write(name + '\n');
        bw.write(email + '\n');
    }
    
    @Override
    public String toString() {
        return name + " (" + email + ")";
    }
    @Override
    public boolean equals(Object o) {
        try {
            if(this == o) return true;
            if(this.getClass() != o.getClass()) return false;
            Customer c = (Customer) o;
            return this.name.equals(c.name) && this.email.equals(c.email);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = 13; 
        result = 31 * result + name.hashCode(); 
        result = 31 * result + email.hashCode(); 
        return result;
    }
    @Override
    public int compareTo(Customer other) {
        int name_comparison = this.name.compareTo(other.name);
        if (name_comparison != 0) {
            return name_comparison;
        } else {
            return this.email.compareTo(other.email);
        }
    }

    private String name;
    private String email;
}

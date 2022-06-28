package academy.pocu.comp2500.lab7;

import java.util.HashMap;

public class Author {
    private final String firstName;
    private final String lastName;

    public Author(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Author) || this.hashCode() != obj.hashCode()) {
            return false;
        }

        Author author = (Author) obj;

        return author.getFullName().equals(this.getFullName());
    }

    @Override
    public int hashCode() {
        return this.firstName.hashCode() ^ (this.lastName.hashCode() << 16);
    }

    @Override
    public String toString() {
        return this.getFullName();
    }
}

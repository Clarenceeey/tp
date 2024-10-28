package seedu.address.model.lesson;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.address.model.consultation.Date;
import seedu.address.model.consultation.Time;
import seedu.address.model.student.Student;
import seedu.address.model.student.exceptions.DuplicateStudentException;

/**
 * Represents a Lesson in the system.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Lesson {

    private final Date date;
    private final Time time;
    private final List<Student> students;

    /**
     * Constructs a {@code Lesson}.
     *
     * @param date     The date of the lesson.
     * @param time     The time of the lesson.
     * @param students A list of students attending the lesson.
     *                 This list can be empty but must not be null.
     * @throws NullPointerException if {@code date} or {@code time} is null.
     */
    public Lesson(Date date, Time time, List<Student> students) {
        requireAllNonNull(date, time);
        this.date = date;
        this.time = time;
        this.students = students != null ? new ArrayList<>(students) : new ArrayList<>();
    }

    /**
     * Constructs a copy of the given lesson.
     * Creates new instances of date, time, and the student list (not the students)
     * to
     * reduce the risk of accidental mutation.
     *
     * @param lesson The lesson to copy.
     */
    public Lesson(Lesson lesson) {
        requireNonNull(lesson);
        this.date = new Date(lesson.getDate().getValue());
        this.time = new Time(lesson.getTime().getValue());
        this.students = new ArrayList<>(lesson.getStudents());
    }

    /**
     * Returns the date of the lesson.
     *
     * @return The date of the lesson.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the time of the lesson.
     *
     * @return The time of the lesson.
     */
    public Time getTime() {
        return time;
    }

    /**
     * Returns an immutable list of students attending the lesson.
     *
     * @return A list of students attending the lesson.
     * @throws UnsupportedOperationException if an attempt is made to modify the
     *                                       returned list.
     */
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    /**
     * Adds a student to the lesson.
     *
     * @param student The student to add.
     */
    public void addStudent(Student student) {
        if (hasStudent(student)) {
            throw new DuplicateStudentException();
        }
        students.add(student);
    }

    /**
     * Removes a student from the lesson.
     *
     * @param student The student to remove.
     */
    public void removeStudent(Student student) {
        students.remove(student);
    }

    /**
     * Returns true if both lessons have the same date, time, and students.
     *
     * @param other The other lesson to compare.
     * @return True if both lessons are the same, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Lesson)) {
            return false;
        }

        Lesson otherLesson = (Lesson) other;
        return date.equals(otherLesson.date)
                && time.equals(otherLesson.time)
                && students.equals(otherLesson.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time, students);
    }

    @Override
    public String toString() {
        return String.format("Lesson[date=%s, time=%s, students=%s]", date, time, students);
    }

    /**
     * Returns true if the lesson contains the specified student.
     *
     * @param student The student to check for.
     * @return True if the student is attending the lesson, false otherwise.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Ensures that none of the arguments passed to the constructor are null.
     *
     * @param objects The objects to check for null values.
     * @throws NullPointerException if any object is null.
     */
    private void requireAllNonNull(Object... objects) {
        for (Object obj : objects) {
            if (obj == null) {
                throw new NullPointerException("Fields date and time must be non-null");
            }
        }
    }
}
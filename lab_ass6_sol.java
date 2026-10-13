import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Lab Assignment 6
 * Problem 1: Course registration portal using multiple collection types.
 * Problem 2: Media player queue using LinkedList and ListIterator.
 *
 * This file can be opened directly as a Java class in IntelliJ IDEA.
 */
public class lab_ass6_sol {

    // ------------------------- Problem 1 -------------------------

    static class Course {
        private final String courseCode;
        private final String courseName;
        private final int capacity;
        private final HashSet<String> enrolledStudentIds;
        private final ArrayList<String> prerequisiteCodes;
        private final LinkedList<String> waitlist;

        public Course(String courseCode, String courseName, int capacity) {
            if (courseCode == null || courseCode.trim().isEmpty()) {
                throw new IllegalArgumentException("Course code cannot be empty.");
            }
            if (courseName == null || courseName.trim().isEmpty()) {
                throw new IllegalArgumentException("Course name cannot be empty.");
            }
            if (capacity <= 0) {
                throw new IllegalArgumentException("Course capacity must be greater than zero.");
            }

            this.courseCode = courseCode;
            this.courseName = courseName;
            this.capacity = capacity;
            this.enrolledStudentIds = new HashSet<>();
            this.prerequisiteCodes = new ArrayList<>();
            this.waitlist = new LinkedList<>();
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String getCourseName() {
            return courseName;
        }

        public int getCapacity() {
            return capacity;
        }

        public HashSet<String> getEnrolledStudentIds() {
            return new HashSet<>(enrolledStudentIds);
        }

        public ArrayList<String> getPrerequisiteCodes() {
            return new ArrayList<>(prerequisiteCodes);
        }

        public LinkedList<String> getWaitlist() {
            return new LinkedList<>(waitlist);
        }

        @Override
        public String toString() {
            return "Course: " + courseCode + " - " + courseName
                    + "\nCapacity: " + capacity
                    + "\nEnrolled students: " + enrolledStudentIds
                    + "\nPrerequisites: " + prerequisiteCodes
                    + "\nWaitlist (first come, first served): " + waitlist;
        }
    }

    static class CourseRegistrationPortal {
        // HashMap provides quick lookup of a course by its code.
        private final HashMap<String, Course> courses = new HashMap<>();

        // Stores the courses in which each student is currently enrolled.
        private final HashMap<String, HashSet<String>> studentCourses = new HashMap<>();

        public boolean addCourse(Course course) {
            if (course == null || courses.containsKey(course.getCourseCode())) {
                return false;
            }
            courses.put(course.getCourseCode(), course);
            return true;
        }

        public boolean addPrerequisite(String courseCode, String prerequisiteCode) {
            Course course = courses.get(courseCode);
            Course prerequisite = courses.get(prerequisiteCode);

            if (course == null || prerequisite == null || courseCode.equals(prerequisiteCode)) {
                return false;
            }
            if (!course.prerequisiteCodes.contains(prerequisiteCode)) {
                // ArrayList keeps prerequisites in the order they were added.
                course.prerequisiteCodes.add(prerequisiteCode);
            }
            return true;
        }

        public String registerStudent(String studentId, String courseCode) {
            if (studentId == null || studentId.trim().isEmpty()) {
                return "Registration failed: student ID cannot be empty.";
            }

            Course course = courses.get(courseCode);
            if (course == null) {
                return "Registration failed: course " + courseCode + " was not found.";
            }

            if (course.enrolledStudentIds.contains(studentId)) {
                return studentId + " is already registered in " + courseCode + ".";
            }
            if (course.waitlist.contains(studentId)) {
                return studentId + " is already on the waitlist for " + courseCode + ".";
            }
            if (!hasCompletedPrerequisites(studentId, course)) {
                return "Registration denied for " + studentId + ": prerequisites are not satisfied for "
                        + courseCode + ".";
            }

            if (course.enrolledStudentIds.size() < course.capacity) {
                enrollStudent(studentId, course);
                return studentId + " successfully registered in " + courseCode + ".";
            }

            // LinkedList is used as a first-come, first-served queue.
            course.waitlist.addLast(studentId);
            return courseCode + " is full. " + studentId + " has been added to the waitlist.";
        }

        public String dropCourse(String studentId, String courseCode) {
            Course course = courses.get(courseCode);
            if (course == null) {
                return "Drop failed: course " + courseCode + " was not found.";
            }

            if (course.waitlist.remove(studentId)) {
                return studentId + " was removed from the waitlist for " + courseCode + ".";
            }

            if (!course.enrolledStudentIds.remove(studentId)) {
                return studentId + " is not enrolled in " + courseCode + ".";
            }

            HashSet<String> enrolledCourses = studentCourses.get(studentId);
            if (enrolledCourses != null) {
                enrolledCourses.remove(courseCode);
                if (enrolledCourses.isEmpty()) {
                    studentCourses.remove(studentId);
                }
            }

            String result = studentId + " dropped " + courseCode + ".";
            String promotedStudent = promoteWaitlistedStudent(course);
            if (promotedStudent != null) {
                result += " " + promotedStudent + " was promoted from the waitlist.";
            }
            return result;
        }

        private boolean hasCompletedPrerequisites(String studentId, Course course) {
            HashSet<String> completedCourses = studentCourses.get(studentId);
            for (String prerequisiteCode : course.prerequisiteCodes) {
                if (completedCourses == null || !completedCourses.contains(prerequisiteCode)) {
                    return false;
                }
            }
            return true;
        }

        private void enrollStudent(String studentId, Course course) {
            course.enrolledStudentIds.add(studentId);
            studentCourses.computeIfAbsent(studentId, key -> new HashSet<>())
                    .add(course.getCourseCode());
        }

        /**
         * Promotes the first eligible student in the queue. If a student's
         * prerequisites are no longer satisfied, that student is moved to the
         * back of the queue so another eligible student can be considered.
         */
        private String promoteWaitlistedStudent(Course course) {
            int studentsToCheck = course.waitlist.size();

            while (course.enrolledStudentIds.size() < course.capacity && studentsToCheck > 0) {
                String studentId = course.waitlist.removeFirst();
                studentsToCheck--;

                if (course.enrolledStudentIds.contains(studentId)) {
                    continue;
                }

                if (hasCompletedPrerequisites(studentId, course)) {
                    enrollStudent(studentId, course);
                    return studentId;
                }

                course.waitlist.addLast(studentId);
            }
            return null;
        }

        public void showCourseDetails(String courseCode) {
            Course course = courses.get(courseCode);
            if (course == null) {
                System.out.println("Course " + courseCode + " was not found.");
            } else {
                System.out.println(course);
            }
        }
    }

    // ------------------------- Problem 2 -------------------------

    static class Song {
        private final String songId;
        private final String title;
        private final String artist;

        public Song(String songId, String title, String artist) {
            if (songId == null || songId.trim().isEmpty()) {
                throw new IllegalArgumentException("Song ID cannot be empty.");
            }
            this.songId = songId;
            this.title = title;
            this.artist = artist;
        }

        public String getSongId() {
            return songId;
        }

        @Override
        public String toString() {
            return title + " - " + artist + " (ID: " + songId + ")";
        }
    }

    static class MediaPlayerQueue {
        private final LinkedList<Song> playlist = new LinkedList<>();
        private final LinkedList<Song> recentHistory = new LinkedList<>();
        private ListIterator<Song> playbackIterator = playlist.listIterator();
        private Song currentSong;
        private int currentIndex = -1;
        private static final int HISTORY_LIMIT = 10;

        /** Inserts a song at a selected position using a ListIterator. */
        public boolean addSongAt(int position, Song song) {
            if (song == null || position < 0 || position > playlist.size()) {
                return false;
            }

            ListIterator<Song> iterator = playlist.listIterator();
            for (int i = 0; i < position; i++) {
                iterator.next();
            }
            iterator.add(song);
            resetPlayback();
            return true;
        }

        /** Adds a song at the beginning so it will be played with priority. */
        public void addPrioritySong(Song song) {
            if (song != null) {
                playlist.addFirst(song);
                resetPlayback();
            }
        }

        /** Removes repeated song IDs in one traversal, keeping the first entry. */
        public int removeDuplicateSongs() {
            HashSet<String> seenSongIds = new HashSet<>();
            Iterator<Song> iterator = playlist.iterator();
            int removedCount = 0;

            while (iterator.hasNext()) {
                Song song = iterator.next();
                if (!seenSongIds.add(song.getSongId())) {
                    iterator.remove();
                    removedCount++;
                }
            }

            resetPlayback();
            return removedCount;
        }

        /** Plays the next song and records it in the recent history. */
        public Song playNext() {
            if (!playbackIterator.hasNext()) {
                return null;
            }

            currentSong = playbackIterator.next();
            currentIndex++;
            addToHistory(currentSong);
            return currentSong;
        }

        /** Plays the previous song without using index-based playlist access. */
        public Song playPrevious() {
            if (currentSong == null || currentIndex <= 0) {
                return null;
            }

            // Move from after the current song to the song before it.
            playbackIterator.previous();
            Song previousSong = playbackIterator.previous();
            playbackIterator.next(); // Leave the iterator after the song just selected.

            currentSong = previousSong;
            currentIndex--;
            addToHistory(currentSong);
            return currentSong;
        }

        private void addToHistory(Song song) {
            recentHistory.addLast(song);
            if (recentHistory.size() > HISTORY_LIMIT) {
                recentHistory.removeFirst();
            }
        }

        private void resetPlayback() {
            // Playlist edits restart navigation from the beginning.
            playbackIterator = playlist.listIterator();
            currentSong = null;
            currentIndex = -1;
        }

        public void displayPlaylistForwardAndBackward() {
            ListIterator<Song> iterator = playlist.listIterator();

            System.out.println("Playlist (forward):");
            while (iterator.hasNext()) {
                System.out.println("  " + iterator.next());
            }

            System.out.println("Playlist (backward):");
            while (iterator.hasPrevious()) {
                System.out.println("  " + iterator.previous());
            }
        }

        public void displayRecentHistory() {
            System.out.println("Recent playback history (oldest to newest):");
            if (recentHistory.isEmpty()) {
                System.out.println("  No songs played yet.");
                return;
            }
            for (Song song : recentHistory) {
                System.out.println("  " + song);
            }
        }
    }

    // ------------------------- Main method / demonstration -------------------------

    public static void main(String[] args) {
        System.out.println("========== PROBLEM 1: COURSE REGISTRATION ==========");

        CourseRegistrationPortal portal = new CourseRegistrationPortal();
        portal.addCourse(new Course("CS101", "Introduction to Programming", 2));
        portal.addCourse(new Course("CS201", "Data Structures", 2));
        portal.addPrerequisite("CS201", "CS101");

        System.out.println(portal.registerStudent("S101", "CS201"));
        System.out.println(portal.registerStudent("S101", "CS101"));
        System.out.println(portal.registerStudent("S101", "CS201"));
        System.out.println(portal.registerStudent("S102", "CS101"));
        System.out.println(portal.registerStudent("S103", "CS101"));
        System.out.println(portal.dropCourse("S102", "CS101"));

        System.out.println("\nFinal course details:");
        portal.showCourseDetails("CS101");
        System.out.println();
        portal.showCourseDetails("CS201");

        System.out.println("\n========== PROBLEM 2: MEDIA PLAYER QUEUE ==========");

        MediaPlayerQueue player = new MediaPlayerQueue();
        player.addSongAt(0, new Song("M01", "First Song", "Artist A"));
        player.addSongAt(1, new Song("M02", "Second Song", "Artist B"));
        player.addSongAt(2, new Song("M03", "Third Song", "Artist C"));
        player.addSongAt(3, new Song("M02", "Second Song (duplicate)", "Artist B"));

        System.out.println("Duplicate entries removed: " + player.removeDuplicateSongs());
        player.addPrioritySong(new Song("M00", "Priority Song", "Artist P"));
        player.displayPlaylistForwardAndBackward();

        System.out.println("\nPlaying next: " + player.playNext());
        System.out.println("Playing next: " + player.playNext());
        System.out.println("Playing previous: " + player.playPrevious());
        System.out.println("Playing next: " + player.playNext());
        player.displayRecentHistory();
    }
}

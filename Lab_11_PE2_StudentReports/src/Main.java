import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Main {
    static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        List<Integer> groupList = new ArrayList<>();
        List<Mark> markList = new ArrayList<>();
        List<Student> absentStudents = new ArrayList<>();

        while (sc.hasNext()) {
            String plab = sc.next();
            if (plab.equals("end")) {
                break;
            }

            String id = sc.next();
            int group = sc.nextInt();
            Student s = new Student(plab, id, group);
            studentList.add(s);
        }

        addToGroupList(groupList, studentList);
        printMarks(studentList, markList, absentStudents);
        printAbsentees(absentStudents);
        printFrequency(studentList, markList);
        printFrequencyWithTutGrp(studentList, markList, groupList);

    }


    public static void addToGroupList(List<Integer> groupList, List<Student> studentList) {
        for (Student s : studentList) {
            if (!groupList.contains(s.getGroup())) {
                groupList.add(s.getGroup());
            }
        }

        groupList.sort((x, y) -> x - y);

        System.out.print("Groups(" + groupList.size() + "):");
        System.out.println(Arrays.toString(groupList.toArray()));
    }

    private static void addMarksAndAbsentees(List<Student> studentList, List<Mark> markList, List<Student> absentStudents) {
        while (sc.hasNext()) {
            String plab = sc.next();
            if (plab.equals("end")) {
                break;
            }

            int mark = sc.nextInt();
            markList.add(new Mark(plab, mark));
        }


        studentList.stream()
                .filter(s -> {
                    for (Mark m : markList) {
                        if (s.getPlab().equals(m.getPlab())) {
                            return false;
                        }
                    }
                    return true;
                })
                .forEach(absentStudents::add);

    }


    public static void printMarks(List<Student> studentList, List<Mark> markList, List<Student> absentStudents) {
        addMarksAndAbsentees(studentList, markList, absentStudents);

        for (int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);
            Optional<Mark> m = markList.stream().filter(x -> x.getPlab().equals(s.getPlab())).findFirst();
            if (m.isPresent()) {
                System.out.println(s + "," + m.get().getMark());
            } else {
                System.out.println(s + "," + 0);
            }

        }
    }

    public static void printAbsentees(List<Student> absentStudents) {
        System.out.println("List of absentees:");
        if (absentStudents.size() == 0) {
            System.out.println("None");
        } else {
            for (Student absentee : absentStudents) {
                System.out.println(absentee);
            }
        }
    }

    public static void printFrequency(List<Student> studentList, List<Mark> markList) {
        int min = markList.stream().mapToInt(m -> m.getMark()).min().getAsInt();
        int max = markList.stream().mapToInt(m -> m.getMark()).max().getAsInt();


        System.out.println("Mark frequency from " + min + " to " + max);
        IntStream range = IntStream.rangeClosed(min, max);
        range.forEach(i -> System.out.println(i + " : " + markList
                .stream()
                .mapToInt(m -> m.getMark())
                .filter(m -> i == m)
                .count()));

    }


    public static void printFrequencyWithTutGrp(List<Student> studentList, List<Mark> markList, List<Integer> groupList) {
        Stream<Integer> range = groupList.stream();
        range.forEach(tutGrp -> {
            System.out.print("Group #" + tutGrp + "...");
            List<String> tutPlabsList = studentList.stream().filter(s -> s.getGroup() == tutGrp).map(s -> s.getPlab()).collect(Collectors.toList());
            List<Mark> tutMarksList = new ArrayList<>();


            for (String plab : tutPlabsList) {
                for (Mark m : markList) {
                    if (m.getPlab().equals(plab)) {
                        tutMarksList.add(m);
                        break;
                    }
                }
            }



            System.out.println("Mark frequency from " + 0 + " to " + 10);
            IntStream zeroToTen  = IntStream.rangeClosed(0, 10);
            zeroToTen.forEach(i -> System.out.println(i + " : " + tutMarksList
                    .stream()
                    .mapToInt(m -> m.getMark())
                    .filter(m -> i == m)
                    .count()));
        });
    }
}





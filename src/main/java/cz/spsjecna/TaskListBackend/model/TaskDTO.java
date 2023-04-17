package cz.spsjecna.TaskListBackend.model;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class TaskDTO {

        public enum Status {
            NOT_STARTED(0),
            ONGOING(1),
            FINISHED(2);

            private int value;
            private static Map map = new HashMap<>();
            Status(int i) {
                this.value = i;
            }
            static {
                for (Priority pageType : Priority.values()) {
                    map.put(pageType.value, pageType);
                }
            }

            public static Status valueOf(int pageType) {
                return (Status) map.get(pageType);
            }

            public int getValue() {
                return value;
            }
        }

        public enum Priority {
            LOW(0),
            MEDIUM(1),
            HIGH(2);
            private int value;
            private static Map map = new HashMap<>();
            Priority(int i) {
                this.value = i;
            }
            static {
                for (Priority pageType : Priority.values()) {
                    map.put(pageType.value, pageType);
                }
            }

            public static Priority valueOf(int pageType) {
                return (Priority) map.get(pageType);
            }

            public int getValue() {
                return value;
            }
        }

        private int id;
        private String name;
        private String description;
        private Date dateFrom;
        private Date dateTo;
        private Status status;
        private Priority priority;






}

export class Urls {
  private static API_PREFIX = '/api';

  private static PERSON_CONTROLLER = Urls.API_PREFIX + '/person';
  public static PERSON_CREATE = Urls.PERSON_CONTROLLER + '/create';
  public static PERSON_GET_ALL = Urls.PERSON_CONTROLLER + '/getAll';
  public static PERSON_GET_BY_EMAIL = Urls.PERSON_CONTROLLER + '/getByEmail';
  public static PERSON_GET_BY_NAME = Urls.PERSON_CONTROLLER + '/getByName';
  public static PERSON_GET_CURRENT = Urls.PERSON_CONTROLLER + '/getCurrent';
  public static PERSON_ASSIGN_ROLE = Urls.PERSON_CONTROLLER + '/assignRole';
  public static PERSON_REMOVE_ROLE = Urls.PERSON_CONTROLLER + '/removeRole';
  public static PERSON_MODIFY = Urls.PERSON_CONTROLLER + '/modify';
  public static PERSON_DELETE = Urls.PERSON_CONTROLLER + '/delete';

  private static PLAY_CONTROLLER = Urls.API_PREFIX + '/play';
  public static PLAY_CREATE = Urls.PLAY_CONTROLLER + '/create';
  public static PLAY_GET_ALL = Urls.PLAY_CONTROLLER + '/getAll';
  public static PLAY_GET_BY_NAME = Urls.PLAY_CONTROLLER + '/getByName';
  public static PLAY_MODIFY = Urls.PLAY_CONTROLLER + '/modify';
  public static PLAY_DELETE = Urls.PLAY_CONTROLLER + '/delete';

  private static RESERVATION_CONTROLLER = Urls.API_PREFIX + '/reservation';
  public static RESERVATION_CREATE = Urls.RESERVATION_CONTROLLER + '/create';
  public static RESERVATION_GET_ALL = Urls.RESERVATION_CONTROLLER + '/getAll';
  public static RESERVATION_GET_BY_PLAY = Urls.RESERVATION_CONTROLLER + '/getByPlay';
  public static RESERVATION_GET_BY_ROOM = Urls.RESERVATION_CONTROLLER + '/getByRoom';
  public static RESERVATION_GET_BY_SCHEDULE = Urls.RESERVATION_CONTROLLER + '/getBySchedule';
  public static RESERVATION_GET_BY_PERSON = Urls.RESERVATION_CONTROLLER + '/getByPerson';
  public static RESERVATION_MODIFY = Urls.RESERVATION_CONTROLLER + '/modify';
  public static RESERVATION_DELETE = Urls.RESERVATION_CONTROLLER + '/delete';

  private static ROOM_CONTROLLER = Urls.API_PREFIX + '/room';
  public static ROOM_CREATE = Urls.ROOM_CONTROLLER + '/create';
  public static ROOM_GET_ALL = Urls.ROOM_CONTROLLER + '/getAll';
  public static ROOM_GET_BY_NAME = Urls.ROOM_CONTROLLER + '/getByName';
  public static ROOM_GET_CAPACITY_LESS_THAN = Urls.ROOM_CONTROLLER + '/getCapacityLessThan';
  public static ROOM_GET_CAPACITY_GREATER_THAN = Urls.ROOM_CONTROLLER + '/getCapacityGreaterThan';
  public static ROOM_GET_CAPACITY_BETWEEN = Urls.ROOM_CONTROLLER + '/getCapacityBetween';
  public static ROOM_MODIFY = Urls.ROOM_CONTROLLER + '/modify';
  public static ROOM_DELETE = Urls.ROOM_CONTROLLER + '/delete';

  private static SCHEDULE_CONTROLLER = Urls.API_PREFIX + '/schedule';
  public static SCHEDULE_CREATE = Urls.SCHEDULE_CONTROLLER + '/create';
  public static SCHEDULE_GET_ALL = Urls.SCHEDULE_CONTROLLER + '/getAll';
  public static SCHEDULE_GET_BY_PLAY = Urls.SCHEDULE_CONTROLLER + '/getByPlay';
  public static SCHEDULE_GET_BY_ROOM = Urls.SCHEDULE_CONTROLLER + '/getByRoom';
  public static SCHEDULE_GET_BEFORE = Urls.SCHEDULE_CONTROLLER + '/getBefore';
  public static SCHEDULE_GET_AFTER = Urls.SCHEDULE_CONTROLLER + '/getAfter';
  public static SCHEDULE_GET_BETWEEN = Urls.SCHEDULE_CONTROLLER + '/getBetween';
  public static SCHEDULE_MODIFY = Urls.SCHEDULE_CONTROLLER + '/modify';
  public static SCHEDULE_DELETE = Urls.SCHEDULE_CONTROLLER + '/delete';
}

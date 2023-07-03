package lab5.collection

interface CollectionManager<T> {
    /**
     * Возвращает информацию о коллекции.
     * @return Информация о коллекции
     */
    fun info() : CollectionInfo

    /**
     * Добавляет элемент в коллекцию.
     * @param item Добавляемый элемент.
     * @return true при успешном добавлении, false при ошибке.
     */
    fun add(item: T) : Boolean

    /**
     * Обновить значение элемента коллекции, id которого равен заданному.
     * @param item Элемент коллекции, который следует обновить.
     * @return true при успешном обновлении, false при ошибке.
     */
    fun updateElementByID(item: T) : Boolean

    /**
     * Очистить коллекцию.
     */
    fun clear()

    /**
     * Вывести элементы коллекции.
     */
    fun show() : Iterable<T>

    /**
     * Удалить элемент из коллекции по его id.
     * @param id Идентификатор элемента.
     * @return true при успешном удалении, false при ошибке.
     */
    fun removeElementByID(id: Int) : Boolean

    /**
     * Сохранить коллекцию в предусмотренный файл.
     * @return true при успешном сохранении, false при ошибке.
     */
    fun saveToFile() : Boolean

    /**
     * Загрузить коллекцию из предусмотренного файла.
     * @return true при успешной загрузке, false при ошибке.
     */
    fun loadFromFile() : Boolean

    /**
     * Добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции.
     * @param item Добавляемый элемент.
     * @return true при успешном добавлении, false при ошибке.
     */
    fun addIfMax(item: T) : Boolean

    /**
     * Добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
     * @param item Добавляемый элемент.
     * @return true при успешном добавлении, false при ошибке.
     */
    fun addIfMin(item: T) : Boolean

    /**
     * Удалить из коллекции все элементы, меньшие, чем заданный.
     * @param item Заданный элемент.
     * @return true при успешном удалении, false при ошибке.
     */
    fun removeLowerThen(item: T) : Boolean

    /**
     * Вывести элемент коллекции, значение поля id которого является максимальным.
     * @return Сам элемент.
     */
    fun returnMaxIdElement(): T?

    /**
     * Вывести элементы коллекции в порядке убывания
     */
    fun returnDescending(): Iterable<T>
}
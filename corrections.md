* Переписать код статических методов на ООП, зависимости должны передаваться через конструктор (чтобы классы были расширяемыми и тестируемыми)
* Сделать вывод более дружелюбным и читаемым для пользователя - юзать пустые строки как разделители, мб заглавные буквы добавить и все такое, чтобы кровь из глаз не текла (в т ч вывод команд help через "-")
* Сделать имя файла из переменной окружения, а не через диалог (как в условии)
* Чем отличаются isRun и isWork в console, почему одной переменной нельзя?
* Имена пакетов в Java называют с маленькой буквы (с большой буквы в Java только имена классов)
* Сделать все точно по условию (если условие кажется бредовым, можно предложить сделать иначе)
* Названия команд в help должны совпадать с тем как их надо вводить (сейчас рассинхрон, чтобы понять что вводить я смотрел в твой код, но пользователь то в код смотреть не сможет)
* Единая точка валидации драконов
* В задании есть условие про сортировку по умолчанию - я так понимаю речь о том чтобы твои драконы реализовывали интерфейс Comparable (и раз такое дело то команды add_if_min и remove_if_greater должны использовать это)
* toString в классе dragon - руками преобразуешь в json, хотя можешь использовать Gson
* Странное распределение классов-исполнителей команд по пакетам и странные название (не совсем понятно почему слово Correct и почему UpdateCollection и PrintInfoCollection в одном пакете, а Correct'ы в другом, а fileManager в третьем)
* CollectionManager removeById - обходить коллекцию до конца после того как удалил нужный элемент не нужно
* Косяк с execute_script, он читает драконов из файла вместо выполнения скрипта (но execute script - задача со звездочкой)
* fileManager строка 32 - неправильно юзается BufferedReader. Чекни этот гайд - https://www.baeldung.com/java-buffered-reader. А чтобы получить детальную инфу по каждому методу, конструктору и т д, лезь в оф документацию по этому классу - https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/BufferedReader.html)
* update id - меняет позицию элемента в коллекции, сделай чтобы он оставался на той же позиции
* update id - я смог добавить несуществующего дракона с id 0
* Пройтись по замечаниям которые делает Intellij (желтые подсказки справа) и возможно исправить, например в классе console предлагает поменять else-if на switch (с Java 12 добавлен новый красивый оператор switch и Intellij может сама переделать код на него)

package indigo
import kotlin.random.Random

fun stworzTalie(talia: MutableList<String>, ranks: List<String>, suits: List<String>) {
    talia.clear()
    for (i in suits) {
        for (j in ranks) {
            talia.add("$j$i")
        }
    }
    talia.shuffle()
}

fun main() {
    val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
    val suits = listOf("♦", "♥", "♠", "♣")

    val talia = mutableListOf<String>()
    val kartyNaStole = mutableListOf<String>()
    val kartyGracza = mutableListOf<String>()
    val kartyKomputera = mutableListOf<String>()
    var czyGraczZaczyna = ""
    var punktyGracza = 0
    var punktyKomputera = 0
    var wygraneKartyGracza = 0
    var wygraneKartyKomputera = 0
    var ktoWygralOstatnio = ""

    stworzTalie(talia, ranks, suits)
    repeat(4) {
        kartyNaStole.add(talia[0])
        talia.removeAt(0)
    }
    repeat(6) {
        kartyGracza.add(talia[0])
        talia.removeAt(0)
        kartyKomputera.add(talia[0])
        talia.removeAt(0)
    }
    println("Indigo Card Game")
    while (czyGraczZaczyna != "yes"  &&  czyGraczZaczyna != "no") {
        println("Play first?")
        czyGraczZaczyna = readln()
    }

    print("Initial cards on the table: ")
    repeat(4) {
        print("${kartyNaStole[it]} ")
    }
    println()
    println()

    if (czyGraczZaczyna == "no") {



        var kartyKandydatow = mutableListOf<Int>()
        if (kartyNaStole.size != 0) {
            repeat(kartyKomputera.size) {
                var naStole_10 = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[0] == '1' && kartyNaStole.last()[1] == '0')) true else false
                var karta_10 = if (kartyKomputera[it][0] == '1' && kartyKomputera[it][1] == '0') true else false
                var wygrana2 = false
                if (naStole_10 && !karta_10) wygrana2 = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[2] == kartyKomputera[it][1])) true else false
                if (!naStole_10 && karta_10) wygrana2 = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[1] == kartyKomputera[it][2])) true else false
                if (!naStole_10 && !karta_10) wygrana2 = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[0] == kartyKomputera[it][0]  ||  kartyNaStole.last()[1] == kartyKomputera[it][1])) true else false
                if (naStole_10 && karta_10) wygrana2 = true
                if (wygrana2) kartyKandydatow.add(it)
            }
        }

        // Wybór karty
        var ktoraKarta = 0
        if (kartyKomputera.size == 1) {
            ktoraKarta = 0
        } else if (kartyKandydatow.size == 1) {
            ktoraKarta = kartyKandydatow[0]
        } else if (kartyNaStole.size == 0  ||  (kartyNaStole.size != 0  &&  kartyKandydatow.size == 0)) {
            var czyJestTakiSamKolor = false
            var kartyTakiSamKolor = mutableListOf<Int>()
            for (i in kartyKomputera.indices) {
                for (j in kartyKomputera.indices) {
                    if (i != j) {
                        if (kartyKomputera[i].last() == kartyKomputera[j].last()) {
                            czyJestTakiSamKolor = true
                            if (i !in kartyTakiSamKolor) kartyTakiSamKolor.add(i)
                            break
                        }
                    }
                }
            }
            if (czyJestTakiSamKolor) {
                ktoraKarta = kartyTakiSamKolor[Random.nextInt(0, kartyTakiSamKolor.size - 1)]
            } else {
                var czyJestTakaSamaWartosc = false
                var kartyTakaSamaWartosc = mutableListOf<Int>()
                for (i in kartyKomputera.indices) {
                    for (j in kartyKomputera.indices) {
                        if (i != j) {
                            if (kartyKomputera[i][0] == kartyKomputera[j][0]) {
                                czyJestTakaSamaWartosc = true
                                if (i !in kartyTakaSamaWartosc) kartyTakaSamaWartosc.add(i)
                                break
                            }
                        }
                    }
                }
                if (czyJestTakaSamaWartosc) {
                    ktoraKarta = kartyTakaSamaWartosc[Random.nextInt(0, kartyTakaSamaWartosc.size - 1)]
                } else {
                    ktoraKarta = Random.nextInt(0, kartyKomputera.size - 1)
                }
            }
        } else {
            var czyJestTakiSamKolor = false
            var kartyTakiSamKolor = mutableListOf<Int>()
            for (i in kartyKandydatow.indices) {
                for (j in kartyKandydatow.indices) {
                    if (i != j) {
                        if (kartyKomputera[kartyKandydatow[i]].last() == kartyKomputera[kartyKandydatow[j]].last()) {
                            czyJestTakiSamKolor = true
                            if (i !in kartyTakiSamKolor) kartyTakiSamKolor.add(i)
                            break
                        }
                    }
                }
            }
            if (czyJestTakiSamKolor) {
                ktoraKarta = kartyTakiSamKolor[Random.nextInt(0, kartyTakiSamKolor.size - 1)]
            } else {
                var czyJestTakaSamaWartosc = false
                var kartyTakaSamaWartosc = mutableListOf<Int>()
                for (i in kartyKandydatow.indices) {
                    for (j in kartyKandydatow.indices) {
                        if (i != j) {
                            if (kartyKomputera[kartyKandydatow[i]][0] == kartyKomputera[kartyKandydatow[j]][0]) {
                                czyJestTakaSamaWartosc = true
                                if (i !in kartyTakaSamaWartosc) kartyTakaSamaWartosc.add(i)
                                break
                            }
                        }
                    }
                }
                if (czyJestTakaSamaWartosc) {
                    ktoraKarta = kartyTakaSamaWartosc[Random.nextInt(0, kartyTakaSamaWartosc.size - 1)]
                } else {
                    ktoraKarta = kartyKandydatow[Random.nextInt(0, kartyKandydatow.size - 1)]
                }
            }
        }



        println("${kartyNaStole.size} cards on the table, and the top card is ${kartyNaStole.last()}")
        repeat(kartyKomputera.size) {
            print("${kartyKomputera[it]} ")
        }
        println()
        println("Computer plays ${kartyKomputera[ktoraKarta]}")
        println()
        var naStole_10 = if (kartyNaStole.last()[0] == '1' && kartyNaStole.last()[1] == '0') true else false
        var karta_10 = if (kartyKomputera[ktoraKarta][0] == '1' && kartyKomputera[ktoraKarta][1] == '0') true else false
        var wygrana = false
        if (naStole_10 && !karta_10) wygrana = if (kartyNaStole.last()[2] == kartyKomputera[ktoraKarta][1]) true else false
        if (!naStole_10 && karta_10) wygrana = if (kartyNaStole.last()[1] == kartyKomputera[ktoraKarta][2]) true else false
        if (!naStole_10 && !karta_10) wygrana = if (kartyNaStole.last()[1] == kartyKomputera[ktoraKarta][1]) true else false
        if (!naStole_10 && !karta_10) wygrana = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[0] == kartyKomputera[ktoraKarta][0]  ||  kartyNaStole.last()[1] == kartyKomputera[ktoraKarta][1])) true else false
        if (naStole_10 && karta_10) wygrana = true
        kartyNaStole.add(kartyKomputera[ktoraKarta])
        kartyKomputera.removeAt(ktoraKarta)
        if (wygrana) {
            repeat(kartyNaStole.size) {
                when (kartyNaStole[it][0]) {
                    'A' -> punktyKomputera++
                    '1' -> if (kartyNaStole[it][1] == '0') punktyKomputera++
                    'J' -> punktyKomputera++
                    'Q' -> punktyKomputera++
                    'K' -> punktyKomputera++
                }
            }
            ktoWygralOstatnio = "Komputer"
            wygraneKartyKomputera += kartyNaStole.size
            kartyNaStole.clear()
            println("Computer wins cards")
            println("Score: Player $punktyGracza - Computer $punktyKomputera")
            println("Cards: Player $wygraneKartyGracza - Computer $wygraneKartyKomputera")
            println()
        }
    }

    while (true) {
        if (wygraneKartyGracza + wygraneKartyKomputera + kartyNaStole.size == 52) {
            if (kartyNaStole.size == 0) {
                println("No cards on the table")
            } else {
                println("${kartyNaStole.size} cards on the table, and the top card is ${kartyNaStole.last()}")
                if (ktoWygralOstatnio == "Gracz") {
                    wygraneKartyGracza += kartyNaStole.size
                    repeat(kartyNaStole.size) {
                        when (kartyNaStole[it][0]) {
                            'A' -> punktyGracza++
                            '1' -> if (kartyNaStole[it][1] == '0') punktyGracza++
                            'J' -> punktyGracza++
                            'Q' -> punktyGracza++
                            'K' -> punktyGracza++
                        }
                    }
                } else {
                    wygraneKartyKomputera += kartyNaStole.size
                    repeat(kartyNaStole.size) {
                        when (kartyNaStole[it][0]) {
                            'A' -> punktyKomputera++
                            '1' -> if (kartyNaStole[it][1] == '0') punktyKomputera++
                            'J' -> punktyKomputera++
                            'Q' -> punktyKomputera++
                            'K' -> punktyKomputera++
                        }
                    }
                }
            }
            break
        }

        if (talia.size != 0) {
            if (kartyGracza.size == 0) {
                repeat(6) {
                    kartyGracza.add(talia[0])
                    talia.removeAt(0)
                }
            }
            if (kartyKomputera.size == 0) {
                repeat(6) {
                    kartyKomputera.add(talia[0])
                    talia.removeAt(0)
                }
            }
        }

        // Gracz
        if (kartyNaStole.size == 0) {
            println("No cards on the table")
        } else {
            println("${kartyNaStole.size} cards on the table, and the top card is ${kartyNaStole.last()}")
        }
        print("Cards in hand: ")
        repeat(kartyGracza.size) {
            print("${it+1})${kartyGracza[it]} ")
        }
        println()
        var karta = 0
        while (karta !in 1..kartyGracza.size) {
            println("Choose a card to play (1-${kartyGracza.size}):")
            val ktoraKarta = readln()
            if (ktoraKarta == "exit") {
                println("Game Over")
                return
            }
            try { karta = ktoraKarta.toInt() } catch (e: NumberFormatException) {}
        }
        println()
        var naStole_10 = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[0] == '1' && kartyNaStole.last()[1] == '0')) true else false
        var karta_10 = if (kartyGracza[karta-1][0] == '1' && kartyGracza[karta-1][1] == '0') true else false
        var wygrana = false
        if (naStole_10 && !karta_10) wygrana = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[2] == kartyGracza[karta-1][1])) true else false
        if (!naStole_10 && karta_10) wygrana = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[1] == kartyGracza[karta-1][2])) true else false
        if (!naStole_10 && !karta_10) wygrana = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[0] == kartyGracza[karta-1][0]  ||  kartyNaStole.last()[1] == kartyGracza[karta-1][1])) true else false
        if (naStole_10 && karta_10) wygrana = true
        kartyNaStole.add(kartyGracza[karta-1])
        kartyGracza.removeAt(karta-1)
        if (wygrana) {
            repeat(kartyNaStole.size) {
                when (kartyNaStole[it][0]) {
                    'A' -> punktyGracza++
                    '1' -> if (kartyNaStole[it][1] == '0') punktyGracza++
                    'J' -> punktyGracza++
                    'Q' -> punktyGracza++
                    'K' -> punktyGracza++
                }
            }
            ktoWygralOstatnio = "Gracz"
            wygraneKartyGracza += kartyNaStole.size
            kartyNaStole.clear()
            println("Player wins cards")
            println("Score: Player $punktyGracza - Computer $punktyKomputera")
            println("Cards: Player $wygraneKartyGracza - Computer $wygraneKartyKomputera")
            println()
        }

        if (wygraneKartyGracza + wygraneKartyKomputera + kartyNaStole.size == 52) {
            if (kartyNaStole.size == 0) {
                println("No cards on the table")
            } else {
                println("${kartyNaStole.size} cards on the table, and the top card is ${kartyNaStole.last()}")
                if (ktoWygralOstatnio == "Gracz") {
                    wygraneKartyGracza += kartyNaStole.size
                    repeat(kartyNaStole.size) {
                        when (kartyNaStole[it][0]) {
                            'A' -> punktyGracza++
                            '1' -> if (kartyNaStole[it][1] == '0') punktyGracza++
                            'J' -> punktyGracza++
                            'Q' -> punktyGracza++
                            'K' -> punktyGracza++
                        }
                    }
                } else {
                    wygraneKartyKomputera += kartyNaStole.size
                    repeat(kartyNaStole.size) {
                        when (kartyNaStole[it][0]) {
                            'A' -> punktyKomputera++
                            '1' -> if (kartyNaStole[it][1] == '0') punktyKomputera++
                            'J' -> punktyKomputera++
                            'Q' -> punktyKomputera++
                            'K' -> punktyKomputera++
                        }
                    }
                }
            }
            break
        }



        // Komputer
        var kartyKandydatow = mutableListOf<Int>()
        if (kartyNaStole.size != 0) {
            repeat(kartyKomputera.size) {
                var naStole_10 = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[0] == '1' && kartyNaStole.last()[1] == '0')) true else false
                var karta_10 = if (kartyKomputera[it][0] == '1' && kartyKomputera[it][1] == '0') true else false
                var wygrana2 = false
                if (naStole_10 && !karta_10) wygrana2 = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[2] == kartyKomputera[it][1])) true else false
                if (!naStole_10 && karta_10) wygrana2 = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[1] == kartyKomputera[it][2])) true else false
                if (!naStole_10 && !karta_10) wygrana2 = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[0] == kartyKomputera[it][0]  ||  kartyNaStole.last()[1] == kartyKomputera[it][1])) true else false
                if (naStole_10 && karta_10) wygrana2 = true
                if (wygrana2) kartyKandydatow.add(it)
            }
        }

        // Wybór karty
        var ktoraKarta = 0
        if (kartyKomputera.size == 1) {
            ktoraKarta = 0
        } else if (kartyKandydatow.size == 1) {
            ktoraKarta = kartyKandydatow[0]
        } else if (kartyNaStole.size == 0  ||  (kartyNaStole.size != 0  &&  kartyKandydatow.size == 0)) {
            var czyJestTakiSamKolor = false
            var kartyTakiSamKolor = mutableListOf<Int>()
            for (i in kartyKomputera.indices) {
                for (j in kartyKomputera.indices) {
                    if (i != j) {
                        if (kartyKomputera[i].last() == kartyKomputera[j].last()) {
                            czyJestTakiSamKolor = true
                            if (i !in kartyTakiSamKolor) kartyTakiSamKolor.add(i)
                            break
                        }
                    }
                }
            }
            if (czyJestTakiSamKolor) {
                ktoraKarta = kartyTakiSamKolor[Random.nextInt(0, kartyTakiSamKolor.size - 1)]
            } else {
                var czyJestTakaSamaWartosc = false
                var kartyTakaSamaWartosc = mutableListOf<Int>()
                for (i in kartyKomputera.indices) {
                    for (j in kartyKomputera.indices) {
                        if (i != j) {
                            if (kartyKomputera[i][0] == kartyKomputera[j][0]) {
                                czyJestTakaSamaWartosc = true
                                if (i !in kartyTakaSamaWartosc) kartyTakaSamaWartosc.add(i)
                                break
                            }
                        }
                    }
                }
                if (czyJestTakaSamaWartosc) {
                    ktoraKarta = kartyTakaSamaWartosc[Random.nextInt(0, kartyTakaSamaWartosc.size - 1)]
                } else {
                    ktoraKarta = Random.nextInt(0, kartyKomputera.size - 1)
                }
            }
        } else {
            var czyJestTakiSamKolor = false
            var kartyTakiSamKolor = mutableListOf<Int>()
            for (i in kartyKandydatow.indices) {
                if (kartyKomputera[kartyKandydatow[i]].last() == kartyNaStole.last().last()) {
                    czyJestTakiSamKolor = true
                    kartyTakiSamKolor.add(kartyKandydatow[i])
                }
            }
            if (kartyTakiSamKolor.size >= 2) {
                ktoraKarta = kartyTakiSamKolor[Random.nextInt(0, kartyTakiSamKolor.size)]
            } else {
                var czyJestTakaSamaWartosc = false
                var kartyTakaSamaWartosc = mutableListOf<Int>()
                for (i in kartyKandydatow.indices) {
                    if (kartyKomputera[kartyKandydatow[i]][0] == kartyNaStole.last().first()) {
                        czyJestTakaSamaWartosc = true
                        kartyTakaSamaWartosc.add(kartyKandydatow[i])
                    }
                }
                if (kartyTakaSamaWartosc.size >= 2) {
                    ktoraKarta = kartyTakaSamaWartosc[Random.nextInt(0, kartyTakaSamaWartosc.size - 1)]
                } else {
                    ktoraKarta = kartyKandydatow[Random.nextInt(0, kartyKandydatow.size - 1)]
                }
            }
        }



        if (kartyNaStole.size == 0) {
            println("No cards on the table")
        } else {
            println("${kartyNaStole.size} cards on the table, and the top card is ${kartyNaStole.last()}")
        }
        repeat(kartyKomputera.size) {
            print("${kartyKomputera[it]} ")
        }
        println()
        println("Computer plays ${kartyKomputera[ktoraKarta]}")
        println()
        naStole_10 = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[0] == '1' && kartyNaStole.last()[1] == '0')) true else false
        karta_10 = if (kartyKomputera[ktoraKarta][0] == '1' && kartyKomputera[ktoraKarta][1] == '0') true else false
        var wygrana2 = false
        if (naStole_10 && !karta_10) wygrana2 = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[2] == kartyKomputera[ktoraKarta][1])) true else false
        if (!naStole_10 && karta_10) wygrana2 = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[1] == kartyKomputera[ktoraKarta][2])) true else false
        if (!naStole_10 && !karta_10) wygrana2 = if (kartyNaStole.size != 0  &&  (kartyNaStole.last()[0] == kartyKomputera[ktoraKarta][0]  ||  kartyNaStole.last()[1] == kartyKomputera[ktoraKarta][1])) true else false
        if (naStole_10 && karta_10) wygrana2 = true
        kartyNaStole.add(kartyKomputera[ktoraKarta])
        kartyKomputera.removeAt(ktoraKarta)
        if (wygrana2) {
            repeat(kartyNaStole.size) {
                when (kartyNaStole[it][0]) {
                    'A' -> punktyKomputera++
                    '1' -> if (kartyNaStole[it][1] == '0') punktyKomputera++
                    'J' -> punktyKomputera++
                    'Q' -> punktyKomputera++
                    'K' -> punktyKomputera++
                }
            }
            ktoWygralOstatnio = "Komputer"
            wygraneKartyKomputera += kartyNaStole.size
            kartyNaStole.clear()
            println("Computer wins cards")
            println("Score: Player $punktyGracza - Computer $punktyKomputera")
            println("Cards: Player $wygraneKartyGracza - Computer $wygraneKartyKomputera")
            println()
        }
    }
    if (wygraneKartyGracza > wygraneKartyKomputera) {
        punktyGracza += 3
    } else if (wygraneKartyGracza < wygraneKartyKomputera) {
        punktyKomputera += 3
    } else {
        if (czyGraczZaczyna == "yes") {
            punktyGracza += 3
        } else {
            punktyKomputera += 3
        }
    }
    println("Score: Player $punktyGracza - Computer $punktyKomputera")
    println("Cards: Player $wygraneKartyGracza - Computer $wygraneKartyKomputera")
    println("Game Over")
}
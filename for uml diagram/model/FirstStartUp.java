package model;
import enums.*;
import operator.DataOperator;
import java.util.ArrayList;

public class FirstStartUp {
	
	public void initialise() {
		DataOperator temp = new DataOperator();
		Staff firststaff = new Staff("staff","password");
		temp.saveNewAdminUser(firststaff);
		
		DataOperator temp2 = new DataOperator();
		Movies firstMovie = new Movies("Avatar",MovieStatus.NOWSHOWING,MovieRating.PG13);
		temp2.saveMovieToDataBase(firstMovie);
		
		/*
		DataOperator temp3 = new DataOperator();
		Movies secondMovie = new Movies("Avengers",MovieStatus.NOWSHOWING,MovieRating.NC16);
		temp3.saveMovieToDataBase(secondMovie);
		
		DataOperator temp4 = new DataOperator();
		Movies thirdmovie = new Movies("Sonic",MovieStatus.NOWSHOWING,MovieRating.PG);
		temp4.saveMovieToDataBase(thirdmovie);
		
		DataOperator temp5 = new DataOperator();
		Movies fourthmovie = new Movies("X-Men",MovieStatus.NOWSHOWING,MovieRating.PG13);
		temp5.saveMovieToDataBase(fourthmovie);
		
		DataOperator temp6 = new DataOperator();
		Movies fifthmovie = new Movies("Black Adam",MovieStatus.NOWSHOWING,MovieRating.PG13);
		temp6.saveMovieToDataBase(fifthmovie);
		
		DataOperator temp7 = new DataOperator();
		Movies sixthmovie = new Movies("Smile",MovieStatus.PREVIEW,MovieRating.NC16);
		temp7.saveMovieToDataBase(sixthmovie);
		
		DataOperator temp8 = new DataOperator();
		Movies seventhmovie = new Movies("Prey for the devil",MovieStatus.PREVIEW,MovieRating.NC16);
		temp8.saveMovieToDataBase(seventhmovie);
		
		DataOperator temp9 = new DataOperator();
		Movies eighthmovie = new Movies("Top Gun: Maverick",MovieStatus.COMINGSOON,MovieRating.NC16);
		temp9.saveMovieToDataBase(eighthmovie);
		
		DataOperator temp10 = new DataOperator();
		Movies ninthmovie = new Movies("Incantation",MovieStatus.COMINGSOON,MovieRating.R21);
		temp10.saveMovieToDataBase(ninthmovie);
		
		DataOperator temp11 = new DataOperator();
		Movies tenthmovie = new Movies("Uncharted",MovieStatus.COMINGSOON,MovieRating.PG13);
		temp11.saveMovieToDataBase(tenthmovie);
		*/
		
	
		DataOperator temp12 = new DataOperator();
		Seat[] seat0 = new Seat[2];
		seat0[0]=new Seat(2,3);
		seat0[1]=new Seat(2,4);
		SeatLayout seatlayout0 = new SeatLayout(8,10,seat0);
		ArrayList<Cinema> temp123 = new ArrayList<Cinema>();
		
		Cineplex temp1= new Cineplex("Cathay",temp123);
		
		
		/*
		Cinema CatheyFirstCinema = new Cinema(temp1,CinemaClassType.NORMAL,3525345,seatlayout0);
		Cinema CatheySecondCinema = new Cinema(temp1,CinemaClassType.NORMAL,3525245,seatlayout0);
		Cinema CatheyThirdCinema = new Cinema(temp1,CinemaClassType.NORMAL,3523445,seatlayout0);
		
		ArrayList<Cinema> cathaycinemas = new ArrayList<Cinema>();
		cathaycinemas.add(CatheyFirstCinema);
		cathaycinemas.add(CatheySecondCinema);
		cathaycinemas.add(CatheyThirdCinema);
		Cineplex cathaycineplex = new Cineplex("Cathay",cathaycinemas);
		ArrayList<Cineplex> Cineplexes  = new ArrayList<Cineplex>();
		Cineplexes.add(cathaycineplex);
		temp12.overwriteCineplexDatabaseWithNewCineplexes(Cineplexes);
		
		DataOperator temp13 = new DataOperator();
		Seat[] seat1 = new Seat[3];
		seat1[0]=new Seat(4,6);
		seat1[1]=new Seat(2,8);
		seat1[2]=new Seat(1,4);
		SeatLayout seatlayout1 = new SeatLayout(9,10,seat1);
		
		Cinema GVFirstCinema = new Cinema(CinemaClassType.GOLD,2525123,seatlayout1);
		Cinema GVSecondCinema = new Cinema(CinemaClassType.GOLD,2525456,seatlayout1);
		Cinema GVThirdCinema = new Cinema(CinemaClassType.GOLD,2523789,seatlayout1);
		ArrayList<Cinema> GVCinemas = new ArrayList<Cinema>();
		GVCinemas.add(GVFirstCinema);
		GVCinemas.add(GVSecondCinema);
		GVCinemas.add(GVThirdCinema);
		Cineplex GVCineplex = new Cineplex("Golden Village",GVCinemas);
		
		ArrayList<Cineplex> Cineplexes  = new ArrayList<Cineplex>();
		Cineplexes.add(cathaycineplex);
		Cineplexes.add(GVCineplex);
		temp13.overwriteCineplexDatabaseWithNewCineplexes(Cineplexes);
		*/
	}
}

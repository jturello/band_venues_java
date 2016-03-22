Band Venues
Epicodus exercise Advanced Database (many to many) Java code review, 03.22.2016

James Turello

Description:
This program tracks bands and the venues where they've played concerts.
  Users can add, update, delete, and list bands.
  Users can add, delete, and list venues.
  The list of all venues displays on the venues page.
  The list of all bands displays on the bands page.
  Users can add venues with a date to a band (i.e., a concert).

Setup

Clone this repository:
```
$ git clone https://github.com/jturello/band_venues_java.git
$ cd into the project's root directory 'band_venues_java'
```

open the file band_venues.sql and change 'Guest' to an owner your Postgres installation recognizes.
then open a terminal window and start Postgres and run the following commands to load the database:
```
$ postgres
  (open another terminal window and cd into the project's root directory 'band_venues_java')
$ CREATE DATABASE band_venues;
$ psql band_venues < band_venues.sql;
$ \c band_venues
```
if you want to run unit and/or integration tests additionally enter the following command:
```
$ CREATE DATABASE band_venues_test WITH TEMPLATE band_venues;
```

Alternatively, to create the databases manually, run the following sql commands:
```
$ psql
$ CREATE DATABASE band_venues;
$ \c band_venues;
$ CREATE TABLE bands (id serial PRIMARY KEY, name VARCHAR, genre VARCHAR);
$ CREATE TABLE venues (id serial PRIMARY KEY, name VARCHAR, location VARCHAR);
$ CREATE TABLE band_venues (id serial PRIMARY KEY, band_id integer REFERENCES bands(id), venue_id integer REFERENCES venues(id), date DATE);
$ CREATE DATABASE band_venues_test WITH TEMPLATE band_venues; (this creates the test database)
```

Then either quit psql (\q) or in another terminal window from the project root directory start the server:
```
$ gradle run
```

Then in your browser enter url:
```
localhost:4567
```


Legal

Copyright (c) 2016, James Turello

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

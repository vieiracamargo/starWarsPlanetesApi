CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS planets (
                                       uuid uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
                                       weather VARCHAR(255) NOT NULL,
                                       terrain VARCHAR(255) NOT NULL,
                                       number_of_appearances_in_movies INTEGER
);

INSERT INTO planets (uuid, name, weather, terrain, number_of_appearances_in_movies)
VALUES
    (uuid_generate_v4(),'Tatooine', 'Sunny', 'Desert', 5),
    (uuid_generate_v4(),'Endor', 'Temperate', 'Forest', 3),
    (uuid_generate_v4(), 'Hoth', 'Frozen', 'Tundra', 2);




CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS planets;

CREATE TABLE planets (
                                       uuid uuid DEFAULT uuid_generate_v4() PRIMARY KEY,
                                       name VARCHAR(255) NOT NULL,
                                       weather VARCHAR(255) NOT NULL,
                                       terrain VARCHAR(255) NOT NULL,
                                       number_of_appearances_in_movies INTEGER
);

INSERT INTO planets (uuid, name, weather, terrain, number_of_appearances_in_movies)
VALUES
    ('123e4567-e89b-12d3-a456-426614174001','Tatooine', 'Sunny', 'Desert', 5),
    ('123e4567-e89b-12d3-a456-426614174002','Endor', 'Temperate', 'Forest', 3),
    ('123e4567-e89b-12d3-a456-426614174003', 'Hoth', 'Frozen', 'Tundra', 2);

CREATE TABLE "lessons" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar
);

CREATE TABLE "vocabularies" (
  "id" SERIAL PRIMARY KEY,
  "name" varchar,
  "romaji" varchar,
  "jp_mm" varchar,
  "meaning" varchar,
  "lesson_id" int
);

CREATE TABLE "favorites" (
  "id" SERIAL PRIMARY KEY,
  "lesson_id" int,
  "vocabulary_id" int
);

ALTER TABLE "vocabularies" ADD FOREIGN KEY ("lesson_id") REFERENCES "lessons" ("id");

ALTER TABLE "favorites" ADD FOREIGN KEY ("lesson_id") REFERENCES "lessons" ("id");

ALTER TABLE "favorites" ADD FOREIGN KEY ("vocabulary_id") REFERENCES "vocabularies" ("id");

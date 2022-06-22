package com.daraprak.BirdieGolf.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerTournamentView {

    String playerName;
    String tournamentName;

}

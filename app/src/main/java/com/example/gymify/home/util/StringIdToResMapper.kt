package com.example.gymify.home.util

import android.content.Context
import com.example.gymify.R
import com.example.gymify.home.data.local.predefined.WorkoutPlanKeys
import kotlin.collections.get

object MuscleGroupIconMapper {
    private val iconMap = mapOf(
        "muscle_chest" to R.drawable.muscle_chest,
        "muscle_back" to R.drawable.muscle_back,
        "muscle_abdominals" to R.drawable.muscle_abdominals,
        "muscle_legs" to R.drawable.muscle_legs,
        "muscle_biceps" to R.drawable.muscle_biceps,
        "muscle_triceps" to R.drawable.muscle_triceps,
        "muscle_shoulders" to R.drawable.muscle_shoulders
    )

    fun getIcon(iconId: String): Int {
        return iconMap[iconId] ?: R.drawable.muscle_chest
    }
}

object MuscleGroupNameMapper {
    private val nameMap = mapOf(
        "muscle_chest" to R.string.muscle_chest,
        "muscle_back" to R.string.muscle_back,
        "muscle_biceps" to R.string.muscle_biceps,
        "muscle_triceps" to R.string.muscle_triceps,
        "muscle_shoulders" to R.string.muscle_shoulders,
        "muscle_legs" to R.string.muscle_legs,
        "muscle_abdominals" to R.string.muscle_abdominals
    )

    fun getName(nameKey: String) : Int {
        return nameMap[nameKey] ?: R.string.no_muscle_group_selected
    }
}


object ExerciseIconMapper {
    private val iconMap = mapOf(
        // All exercises (119)
        // Back exercises (22)
        "ic_back_barbell_row_overhand_start" to R.drawable.ic_back_barbell_row_overhand_start, "ic_back_barbell_row_overhand_end" to R.drawable.ic_back_barbell_row_overhand_end,

        "ic_back_barbell_sumo_deadlift_start" to R.drawable.ic_back_barbell_sumo_deadlift_start, "ic_back_barbell_sumo_deadlift_end" to R.drawable.ic_back_barbell_sumo_deadlift_end,

        "ic_back_seated_cable_row_start" to R.drawable.ic_back_seated_cable_row_start, "ic_back_seated_cable_row_end" to R.drawable.ic_back_seated_cable_row_end,

        "ic_back_t_bar_row_start" to R.drawable.ic_back_t_bar_row_start, "ic_back_t_bar_row_end" to R.drawable.ic_back_t_bar_row_end,

        "ic_back_lat_pulldown_wide_grip_start" to R.drawable.ic_back_lat_pulldown_wide_grip_start, "ic_back_lat_pulldown_wide_grip_end" to R.drawable.ic_back_lat_pulldown_wide_grip_end,

        "ic_back_lat_pulldown_close_grip_underhand_start" to R.drawable.ic_back_lat_pulldown_close_grip_underhand_start, "ic_back_lat_pulldown_close_grip_underhand_end" to R.drawable.ic_back_lat_pulldown_close_grip_underhand_end,

        "ic_back_dumbbell_row_start" to R.drawable.ic_back_dumbbell_row_start, "ic_back_dumbbell_row_end" to R.drawable.ic_back_dumbbell_row_end,

        "ic_back_barbell_row_underhand_start" to R.drawable.ic_back_barbell_row_underhand_start, "ic_back_barbell_row_underhand_end" to R.drawable.ic_back_barbell_row_underhand_end,

        "ic_back_cable_pullover_start" to R.drawable.ic_back_cable_pullover_start, "ic_back_cable_pullover_end" to R.drawable.ic_back_cable_pullover_end,

        "ic_back_pulldown_underhand_start" to R.drawable.ic_back_pulldown_underhand_start, "ic_back_pulldown_underhand_end" to R.drawable.ic_back_pulldown_underhand_end,

        "ic_back_two_arm_dumbbell_row_start" to R.drawable.ic_back_two_arm_dumbbell_row_start, "ic_back_two_arm_dumbbell_row_end" to R.drawable.ic_back_two_arm_dumbbell_row_end,

        "ic_back_pullup_start" to R.drawable.ic_back_pullup_start, "ic_back_pullup_end" to R.drawable.ic_back_pullup_end,

        "ic_back_pullup_underhand_start" to R.drawable.ic_back_pullup_underhand_start, "ic_back_pullup_underhand_end" to R.drawable.ic_back_pullup_underhand_end,

        "ic_back_barbell_shrug_start" to R.drawable.ic_back_barbell_shrug_start, "ic_back_barbell_shrug_end" to R.drawable.ic_back_barbell_shrug_end,

        "ic_back_barbell_deadlift_start" to R.drawable.ic_back_barbell_deadlift_start, "ic_back_barbell_deadlift_end" to R.drawable.ic_back_barbell_deadlift_end,

        "ic_back_trap_bar_deadlift_start" to R.drawable.ic_back_trap_bar_deadlift_start, "ic_back_trap_bar_deadlift_end" to R.drawable.ic_back_trap_bar_deadlift_end,

        "ic_back_lat_pulldown_behind_neck_start" to R.drawable.ic_back_lat_pulldown_behind_neck_start, "ic_back_lat_pulldown_behind_neck_end" to R.drawable.ic_back_lat_pulldown_behind_neck_end,

        "ic_back_barbell_pullover_start" to R.drawable.ic_back_barbell_pullover_start, "ic_back_barbell_pullover_end" to R.drawable.ic_back_barbell_pullover_end,

        "ic_back_dumbbell_deadlift_start" to R.drawable.ic_back_dumbbell_deadlift_start, "ic_back_dumbbell_deadlift_end" to R.drawable.ic_back_dumbbell_deadlift_end,

        "ic_back_dumbbell_pullover_start" to R.drawable.ic_back_dumbbell_pullover_start, "ic_back_dumbbell_pullover_end" to R.drawable.ic_back_dumbbell_pullover_end,

        "ic_back_dumbbell_shrugs_start" to R.drawable.ic_back_dumbbell_shrugs_start, "ic_back_dumbbell_shrugs_end" to R.drawable.ic_back_dumbbell_shrugs_end,

        "ic_back_extension_start" to R.drawable.ic_back_extension_start, "ic_back_extension_end" to R.drawable.ic_back_extension_end,

        // Biceps exercises (12)
        "ic_biceps_barbell_curl_start" to R.drawable.ic_biceps_barbell_curl_start, "ic_biceps_barbell_curl_end" to R.drawable.ic_biceps_barbell_curl_end,

        "ic_biceps_ez_barbell_curl_start" to R.drawable.ic_biceps_ez_barbell_curl_start, "ic_biceps_ez_barbell_curl_end" to R.drawable.ic_biceps_ez_barbell_curl_end,

        "ic_biceps_preacher_barbell_curl_start" to R.drawable.ic_biceps_preacher_barbell_curl_start, "ic_biceps_preacher_barbell_curl_end" to R.drawable.ic_biceps_preacher_barbell_curl_end,

        "ic_biceps_rope_cable_curl_start" to R.drawable.ic_biceps_rope_cable_curl_start, "ic_biceps_rope_cable_curl_end" to R.drawable.ic_biceps_rope_cable_curl_end,

        "ic_biceps_dumbbell_alternate_curl_start" to R.drawable.ic_biceps_dumbbell_alternate_curl_start, "ic_biceps_dumbbell_alternate_curl_end" to R.drawable.ic_biceps_dumbbell_alternate_curl_end,

        "ic_biceps_dumbbell_concentration_curl_start" to R.drawable.ic_biceps_dumbbell_concentration_curl_start, "ic_biceps_dumbbell_concentration_curl_end" to R.drawable.ic_biceps_dumbbell_concentration_curl_end,

        "ic_biceps_incline_dumbbell_curl_start" to R.drawable.ic_biceps_incline_dumbbell_curl_start, "ic_biceps_incline_dumbbell_curl_end" to R.drawable.ic_biceps_incline_dumbbell_curl_end,

        "ic_biceps_single_arm_low_cable_curl_start" to R.drawable.ic_biceps_single_arm_low_cable_curl_start, "ic_biceps_single_arm_low_cable_curl_end" to R.drawable.ic_biceps_single_arm_low_cable_curl_end,

        "ic_biceps_standing_two_arm_high_cable_curl_start" to R.drawable.ic_biceps_standing_two_arm_high_cable_curl_start, "ic_biceps_standing_two_arm_high_cable_curl_end" to R.drawable.ic_biceps_standing_two_arm_high_cable_curl_end,

        "ic_biceps_straight_bar_low_cable_curl_start" to R.drawable.ic_biceps_straight_bar_low_cable_curl_start, "ic_biceps_straight_bar_low_cable_curl_end" to R.drawable.ic_biceps_straight_bar_low_cable_curl_end,

        "ic_biceps_dumbbell_crossbody_hammer_curl_start" to R.drawable.ic_biceps_dumbbell_crossbody_hammer_curl_start, "ic_biceps_dumbbell_crossbody_hammer_curl_end" to R.drawable.ic_biceps_dumbbell_crossbody_hammer_curl_end,

        "ic_biceps_barbell_reverse_curl_start" to R.drawable.ic_biceps_barbell_reverse_curl_start, "ic_biceps_barbell_reverse_curl_end" to R.drawable.ic_biceps_barbell_reverse_curl_end,

        // Triceps exercises (13)
        "ic_triceps_parallel_dip_bar_start" to R.drawable.ic_triceps_parallel_dip_bar_start, "ic_triceps_parallel_dip_bar_end" to R.drawable.ic_triceps_parallel_dip_bar_end,

        "ic_triceps_seated_barbell_french_press_start" to R.drawable.ic_triceps_seated_barbell_french_press_start, "ic_triceps_seated_barbell_french_press_end" to R.drawable.ic_triceps_seated_barbell_french_press_end,

        "ic_triceps_underhand_bar_cable_extension_start" to R.drawable.ic_triceps_underhand_bar_cable_extension_start, "ic_triceps_underhand_bar_cable_extension_end" to R.drawable.ic_triceps_underhand_bar_cable_extension_end,

        "ic_triceps_single_arm_cable_extension_overhand_start" to R.drawable.ic_triceps_single_arm_cable_extension_overhand_start, "ic_triceps_single_arm_cable_extension_overhand_end" to R.drawable.ic_triceps_single_arm_cable_extension_overhand_end,

        "ic_triceps_single_arm_cable_extension_underhand_start" to R.drawable.ic_triceps_single_arm_cable_extension_underhand_start, "ic_triceps_single_arm_cable_extension_underhand_end" to R.drawable.ic_triceps_single_arm_cable_extension_underhand_end,

        "ic_triceps_bench_dip_start" to R.drawable.ic_triceps_bench_dip_start, "ic_triceps_bench_dip_end" to R.drawable.ic_triceps_bench_dip_end,

        "ic_triceps_seated_dumbbell_overhead_extension_start" to R.drawable.ic_triceps_seated_dumbbell_overhead_extension_start, "ic_triceps_seated_dumbbell_overhead_extension_end" to R.drawable.ic_triceps_seated_dumbbell_overhead_extension_end,

        "ic_triceps_dumbbell_kickback_start" to R.drawable.ic_triceps_dumbbell_kickback_start, "ic_triceps_dumbbell_kickback_end" to R.drawable.ic_triceps_dumbbell_kickback_end,

        "ic_triceps_dumbbell_french_press_start" to R.drawable.ic_triceps_dumbbell_french_press_start, "ic_triceps_dumbbell_french_press_end" to R.drawable.ic_triceps_dumbbell_french_press_end,

        "ic_triceps_cable_rope_pushdown_start" to R.drawable.ic_triceps_cable_rope_pushdown_start, "ic_triceps_cable_rope_pushdown_end" to R.drawable.ic_triceps_cable_rope_pushdown_end,

        "ic_triceps_barbell_french_press_start" to R.drawable.ic_triceps_barbell_french_press_start, "ic_triceps_barbell_french_press_end" to R.drawable.ic_triceps_barbell_french_press_end,

        "ic_triceps_close_grip_bench_press_start" to R.drawable.ic_triceps_close_grip_bench_press_start, "ic_triceps_close_grip_bench_press_end" to R.drawable.ic_triceps_close_grip_bench_press_end,

        "ic_triceps_bar_pushdown_overhand_start" to R.drawable.ic_triceps_bar_pushdown_overhand_start, "ic_triceps_bar_pushdown_overhand_end" to R.drawable.ic_triceps_bar_pushdown_overhand_end,

        // Chest exercises (11)
        "ic_chest_push_up_start" to R.drawable.ic_chest_push_up_start, "ic_chest_push_up_end" to R.drawable.ic_chest_push_up_end,

        "ic_chest_incline_dumbbell_fly_start" to R.drawable.ic_chest_incline_dumbbell_fly_start, "ic_chest_incline_dumbbell_fly_end" to R.drawable.ic_chest_incline_dumbbell_fly_end,

        "ic_chest_barbell_declined_bench_press_start" to R.drawable.ic_chest_barbell_declined_bench_press_start,

        "ic_chest_dumbbell_bench_press_start" to R.drawable.ic_chest_dumbbell_bench_press_start, "ic_chest_dumbbell_bench_press_end" to R.drawable.ic_chest_dumbbell_bench_press_end,

        "ic_chest_barbell_bench_press" to R.drawable.ic_chest_barbell_bench_press,

        "ic_chest_press_machine" to R.drawable.ic_chest_press_machine,

        "ic_chest_cable_crossover_start" to R.drawable.ic_chest_cable_crossover_start, "ic_chest_cable_crossover_end" to R.drawable.ic_chest_cable_crossover_end,

        "ic_chest_flat_dumbbell_fly_start" to R.drawable.ic_chest_flat_dumbbell_fly_start, "ic_chest_flat_dumbbell_fly_end" to R.drawable.ic_chest_flat_dumbbell_fly_end,

        "ic_chest_incline_barbell_bench_press_start" to R.drawable.ic_chest_incline_barbell_bench_press_start, "ic_chest_incline_barbell_bench_press_end" to R.drawable.ic_chest_incline_barbell_bench_press_end,

        "ic_chest_peck_deck_fly_start" to R.drawable.ic_chest_peck_deck_fly_start, "ic_chest_peck_deck_fly_end" to R.drawable.ic_chest_peck_deck_fly_end,

        "ic_chest_dumbbell_declined_bench_press" to R.drawable.ic_chest_dumbbell_declined_bench_press,

        // Shoulders (14)
        "ic_shoulders_smith_machine_press_start" to R.drawable.ic_shoulders_smith_machine_press_start, "ic_shoulders_smith_machine_press_end" to R.drawable.ic_shoulders_smith_machine_press_end,

        "ic_shoulders_barbell_push_press_start" to R.drawable.ic_shoulders_barbell_push_press_start, "ic_shoulders_barbell_push_press_end" to R.drawable.ic_shoulders_barbell_push_press_end,

        "ic_shoulders_dumbbell_lateral_raise_start" to R.drawable.ic_shoulders_dumbbell_lateral_raise_start, "ic_shoulders_dumbbell_lateral_raise_end" to R.drawable.ic_shoulders_dumbbell_lateral_raise_end,

        "ic_shoulders_dumbbell_front_raise_start" to R.drawable.ic_shoulders_dumbbell_front_raise_start, "ic_shoulders_dumbbell_front_raise_end" to R.drawable.ic_shoulders_dumbbell_front_raise_end,

        "ic_shoulders_seated_dumbbell_press_start" to R.drawable.ic_shoulders_seated_dumbbell_press_start, "ic_shoulders_seated_dumbbell_press_end" to R.drawable.ic_shoulders_seated_dumbbell_press_end,

        "ic_shoulders_barbell_upright_row_start" to R.drawable.ic_shoulders_barbell_upright_row_start, "ic_shoulders_barbell_upright_row_end" to R.drawable.ic_shoulders_barbell_upright_row_end,

        "ic_shoulders_standing_bent_over_lateral_raise_start" to R.drawable.ic_shoulders_standing_bent_over_lateral_raise_start, "ic_shoulders_standing_bent_over_lateral_raise_end" to R.drawable.ic_shoulders_standing_bent_over_lateral_raise_end,

        "ic_shoulders_single_arm_cable_front_raise_start" to R.drawable.ic_shoulders_single_arm_cable_front_raise_start, "ic_shoulders_single_arm_cable_front_raise_end" to R.drawable.ic_shoulders_single_arm_cable_front_raise_end,

        "ic_shoulders_high_cable_rear_delt_fly_start" to R.drawable.ic_shoulders_high_cable_rear_delt_fly_start, "ic_shoulders_high_cable_rear_delt_fly_end" to R.drawable.ic_shoulders_high_cable_rear_delt_fly_end,

        "ic_shoulders_single_arm_cable_lateral_raise_start" to R.drawable.ic_shoulders_single_arm_cable_lateral_raise_start, "ic_shoulders_single_arm_cable_lateral_raise_end" to R.drawable.ic_shoulders_single_arm_cable_lateral_raise_end,

        "ic_shoulders_dumbbell_push_press_start" to R.drawable.ic_shoulders_dumbbell_push_press_start, "ic_shoulders_dumbbell_push_press_end" to R.drawable.ic_shoulders_dumbbell_push_press_end,

        "ic_shoulders_barbell_front_raise_start" to R.drawable.ic_shoulders_barbell_front_raise_start, "ic_shoulders_barbell_front_raise_end" to R.drawable.ic_shoulders_barbell_front_raise_end,

        "ic_shoulders_seated_barbell_press_start" to R.drawable.ic_shoulders_seated_barbell_press_start, "ic_shoulders_seated_barbell_press_end" to R.drawable.ic_shoulders_seated_barbell_press_end,

        "ic_shoulders_two_handed_dumbbell_front_raise_start" to R.drawable.ic_shoulders_two_handed_dumbbell_front_raise_start, "ic_shoulders_two_handed_dumbbell_front_raise_end" to R.drawable.ic_shoulders_two_handed_dumbbell_front_raise_end,

        // Abdominals (10)
        "ic_abs_crunch_machine_start" to R.drawable.ic_abs_crunch_machine_start, "ic_abs_crunch_machine_end" to R.drawable.ic_abs_crunch_machine_end,

        "ic_abs_crunch_start" to R.drawable.ic_abs_crunch_start, "ic_abs_crunch_end" to R.drawable.ic_abs_crunch_end,

        "ic_abs_oblique_crunch_start" to R.drawable.ic_abs_oblique_crunch_start, "ic_abs_oblique_crunch_end" to R.drawable.ic_abs_oblique_crunch_end,

        "ic_abs_reverse_crunch_start" to R.drawable.ic_abs_reverse_crunch_start, "ic_abs_reverse_crunch_end" to R.drawable.ic_abs_reverse_crunch_end,

        "ic_abs_hanging_leg_raise_start" to R.drawable.ic_abs_hanging_leg_raise_start, "ic_abs_hanging_leg_raise_end" to R.drawable.ic_abs_hanging_leg_raise_end,

        "ic_abs_rope_pulldown_start" to R.drawable.ic_abs_rope_pulldown_start, "ic_abs_rope_pulldown_end" to R.drawable.ic_abs_rope_pulldown_end,

        "ic_abs_plank_get_up_start" to R.drawable.ic_abs_plank_get_up_start, "ic_abs_plank_get_up_end" to R.drawable.ic_abs_plank_get_up_end,

        "ic_abs_wheel_rollout_start" to R.drawable.ic_abs_wheel_rollout_start, "ic_abs_wheel_rollout_end" to R.drawable.ic_abs_wheel_rollout_end,

        "ic_abs_decline_leg_raise_start" to R.drawable.ic_abs_decline_leg_raise_start, "ic_abs_decline_leg_raise_end" to R.drawable.ic_abs_decline_leg_raise_end,

        "ic_abs_plank" to R.drawable.ic_abs_plank,

        // Legs (37)
        "ic_legs_extension_start" to R.drawable.ic_legs_extension_start, "ic_legs_extension_end" to R.drawable.ic_legs_extension_end,

        "ic_legs_barbell_squat_start" to R.drawable.ic_legs_barbell_squat_start, "ic_legs_barbell_squat_end" to R.drawable.ic_legs_barbell_squat_end,

        "ic_legs_barbell_romanian_deadlift_start" to R.drawable.ic_legs_barbell_romanian_deadlift_start, "ic_legs_barbell_romanian_deadlift_end" to R.drawable.ic_legs_barbell_romanian_deadlift_end,

        "ic_legs_glute_ham_raise_start" to R.drawable.ic_legs_glute_ham_raise_start, "ic_legs_glute_ham_raise_end" to R.drawable.ic_legs_glute_ham_raise_end,

        "ic_legs_standing_resistance_band_kickback_start" to R.drawable.ic_legs_standing_resistance_band_kickback_start, "ic_legs_standing_resistance_band_kickback_end" to R.drawable.ic_legs_standing_resistance_band_kickback_end,

        "ic_legs_lunge_start" to R.drawable.ic_legs_lunge_start, "ic_legs_lunge_end" to R.drawable.ic_legs_lunge_end,

        "ic_legs_barbell_good_morning_start" to R.drawable.ic_legs_barbell_good_morning_start, "ic_legs_barbell_good_morning_end" to R.drawable.ic_legs_barbell_good_morning_end,

        "ic_legs_dumbbell_goblet_squat_start" to R.drawable.ic_legs_dumbbell_goblet_squat_start, "ic_legs_dumbbell_goblet_squat_end" to R.drawable.ic_legs_dumbbell_goblet_squat_end,

        "ic_legs_dumbbell_step_up_start" to R.drawable.ic_legs_dumbbell_step_up_start, "ic_legs_dumbbell_step_up_end" to R.drawable.ic_legs_dumbbell_step_up_end,

        "ic_legs_donkey_kicks_start" to R.drawable.ic_legs_donkey_kicks_start, "ic_legs_donkey_kicks_end" to R.drawable.ic_legs_donkey_kicks_end,

        "ic_legs_hack_squat_start" to R.drawable.ic_legs_hack_squat_start, "ic_legs_hack_squat_end" to R.drawable.ic_legs_hack_squat_end,

        "ic_legs_lying_leg_curl_start" to R.drawable.ic_legs_lying_leg_curl_start, "ic_legs_lying_leg_curl_end" to R.drawable.ic_legs_lying_leg_curl_end,

        "ic_legs_seated_leg_curl_start" to R.drawable.ic_legs_seated_leg_curl_start, "ic_legs_seated_leg_curl_end" to R.drawable.ic_legs_seated_leg_curl_end,

        "ic_legs_kettlebell_swing_start" to R.drawable.ic_legs_kettlebell_swing_start, "ic_legs_kettlebell_swing_end" to R.drawable.ic_legs_kettlebell_swing_end,

        "ic_legs_side_lying_leg_raise_start" to R.drawable.ic_legs_side_lying_leg_raise_start, "ic_legs_side_lying_leg_raise_end" to R.drawable.ic_legs_side_lying_leg_raise_end,

        "ic_legs_banded_glute_bridge_start" to R.drawable.ic_legs_banded_glute_bridge_start, "ic_legs_banded_glute_bridge_end" to R.drawable.ic_legs_banded_glute_bridge_end,

        "ic_legs_bodyweight_glute_bridge_start" to R.drawable.ic_legs_bodyweight_glute_bridge_start, "ic_legs_bodyweight_glute_bridge_end" to R.drawable.ic_legs_bodyweight_glute_bridge_end,

        "ic_legs_single_leg_extension_start" to R.drawable.ic_legs_single_leg_extension_start, "ic_legs_single_leg_extension_end" to R.drawable.ic_legs_single_leg_extension_end,

        "ic_legs_lateral_band_walk_start" to R.drawable.ic_legs_lateral_band_walk_start, "ic_legs_lateral_band_walk_end" to R.drawable.ic_legs_lateral_band_walk_end,

        "ic_legs_barbell_front_squat_start" to R.drawable.ic_legs_barbell_front_squat_start, "ic_legs_barbell_front_squat_end" to R.drawable.ic_legs_barbell_front_squat_end,

        "ic_legs_dumbbell_romanian_deadlift_start" to R.drawable.ic_legs_dumbbell_romanian_deadlift_start, "ic_legs_dumbbell_romanian_deadlift_end" to R.drawable.ic_legs_dumbbell_romanian_deadlift_end,

        "ic_legs_bodyweight_squat_start" to R.drawable.ic_legs_bodyweight_squat_start, "ic_legs_bodyweight_squat_end" to R.drawable.ic_legs_bodyweight_squat_end,

        "ic_legs_medicine_ball_squat_start" to R.drawable.ic_legs_medicine_ball_squat_start, "ic_legs_medicine_ball_squat_end" to R.drawable.ic_legs_medicine_ball_squat_end,

        "ic_legs_barbell_bulgarian_split_squat_start" to R.drawable.ic_legs_barbell_bulgarian_split_squat_start, "ic_legs_barbell_bulgarian_split_squat_end" to R.drawable.ic_legs_barbell_bulgarian_split_squat_end,

        "ic_legs_kettlebell_sumo_deadlift_start" to R.drawable.ic_legs_kettlebell_sumo_deadlift_start, "ic_legs_kettlebell_sumo_deadlift_end" to R.drawable.ic_legs_kettlebell_sumo_deadlift_end,

        "ic_legs_single_leg_bodyweight_deadlift_start" to R.drawable.ic_legs_single_leg_bodyweight_deadlift_start, "ic_legs_single_leg_bodyweight_deadlift_end" to R.drawable.ic_legs_single_leg_bodyweight_deadlift_end,

        "ic_legs_barbell_hip_thrust_start" to R.drawable.ic_legs_barbell_hip_thrust_start, "ic_legs_barbell_hip_thrust_end" to R.drawable.ic_legs_barbell_hip_thrust_end,

        "ic_legs_mini_band_squat_start" to R.drawable.ic_legs_mini_band_squat_start, "ic_legs_mini_band_squat_end" to R.drawable.ic_legs_mini_band_squat_end,

        "ic_legs_cable_hip_abduction_start" to R.drawable.ic_legs_cable_hip_abduction_start, "ic_legs_cable_hip_abduction_end" to R.drawable.ic_legs_cable_hip_abduction_end,

        "ic_legs_jump_squat_start" to R.drawable.ic_legs_jump_squat_start, "ic_legs_jump_squat_end" to R.drawable.ic_legs_jump_squat_end,

        "ic_legs_wall_sit" to R.drawable.ic_legs_wall_sit,

        "ic_legs_banded_clamshell_start" to R.drawable.ic_legs_banded_clamshell_start, "ic_legs_banded_clamshell_end" to R.drawable.ic_legs_banded_clamshell_end,

        "ic_legs_bodyweight_bulgarian_split_squat_start" to R.drawable.ic_legs_bodyweight_bulgarian_split_squat_start, "ic_legs_bodyweight_bulgarian_split_squat_end" to R.drawable.ic_legs_bodyweight_bulgarian_split_squat_end,

        "ic_legs_leg_press_start" to R.drawable.ic_legs_leg_press_start, "ic_legs_leg_press_end" to R.drawable.ic_legs_leg_press_end,

        "ic_legs_medicine_ball_deadlift_start" to R.drawable.ic_legs_medicine_ball_deadlift_start, "ic_legs_medicine_ball_deadlift_end" to R.drawable.ic_legs_medicine_ball_deadlift_end,

        "ic_legs_seated_calf_raise_start" to R.drawable.ic_legs_seated_calf_raise_start, "ic_legs_seated_calf_raise_end" to R.drawable.ic_legs_seated_calf_raise_end,

        "ic_legs_standing_calf_raise_start" to R.drawable.ic_legs_standing_calf_raise_start, "ic_legs_standing_calf_raise_end" to R.drawable.ic_legs_standing_calf_raise_end
        )


    fun getIcon(exerciseId: String?): Int {
        return iconMap[exerciseId] ?: R.drawable.icon_top_app_bar // Иконка по умолчанию
    }
}

object ExerciseNameMapper {
    private val nameMap = mapOf(
        // Back
        "back_barbell_row_overhand" to R.string.exercise_back_barbell_row_overhand,
        "back_barbell_sumo_deadlift" to R.string.exercise_back_barbell_sumo_deadlift,
        "back_seated_cable_row" to R.string.exercise_back_seated_cable_row,
        "back_t_bar_row" to R.string.exercise_back_t_bar_row,
        "back_lat_pulldown_wide_grip" to R.string.exercise_back_lat_pulldown_wide_grip,
        "back_lat_pulldown_close_grip_underhand" to R.string.exercise_back_lat_pulldown_close_grip_underhand,
        "back_dumbbell_row" to R.string.exercise_back_dumbbell_row,
        "back_barbell_row_underhand" to R.string.exercise_back_barbell_row_underhand,
        "back_cable_pullover" to R.string.exercise_back_cable_pullover,
        "back_pulldown_underhand" to R.string.exercise_back_pulldown_underhand,
        "back_two_arm_dumbbell_row" to R.string.exercise_back_two_arm_dumbbell_row,
        "back_pullup" to R.string.exercise_back_pullup,
        "back_pullup_underhand" to R.string.exercise_back_pullup_underhand,
        "back_barbell_shrug" to R.string.exercise_back_barbell_shrug,
        "back_barbell_deadlift" to R.string.exercise_back_barbell_deadlift,
        "back_trap_bar_deadlift" to R.string.exercise_back_trap_bar_deadlift,
        "back_lat_pulldown_behind_neck" to R.string.exercise_back_lat_pulldown_behind_neck,
        "back_barbell_pullover" to R.string.exercise_back_barbell_pullover,
        "back_dumbbell_deadlift" to R.string.exercise_back_dumbbell_deadlift,
        "back_dumbbell_pullover" to R.string.exercise_back_dumbbell_pullover,
        "back_dumbbell_shrugs" to R.string.exercise_back_dumbbell_shrugs,
        "back_extension" to R.string.exercise_back_extension,

        // Biceps
        "biceps_barbell_curl" to R.string.exercise_biceps_barbell_curl,
        "biceps_ez_barbell_curl" to R.string.exercise_biceps_ez_barbell_curl,
        "biceps_preacher_barbell_curl" to R.string.exercise_biceps_preacher_barbell_curl,
        "biceps_rope_cable_curl" to R.string.exercise_biceps_rope_cable_curl,
        "biceps_dumbbell_alternate_curl" to R.string.exercise_biceps_dumbbell_alternate_curl,
        "biceps_dumbbell_concentration_curl" to R.string.exercise_biceps_dumbbell_concentration_curl,
        "biceps_incline_dumbbell_curl" to R.string.exercise_biceps_incline_dumbbell_curl,
        "biceps_single_arm_low_cable_curl" to R.string.exercise_biceps_single_arm_low_cable_curl,
        "biceps_standing_two_arm_high_cable_curl" to R.string.exercise_biceps_standing_two_arm_high_cable_curl,
        "biceps_straight_bar_low_cable_curl" to R.string.exercise_biceps_straight_bar_low_cable_curl,
        "biceps_dumbbell_crossbody_hammer_curl" to R.string.exercise_biceps_dumbbell_crossbody_hammer_curl,
        "biceps_barbell_reverse_curl" to R.string.exercise_biceps_barbell_reverse_curl,

        // Triceps
        "triceps_parallel_dip_bar" to R.string.exercise_triceps_parallel_dip_bar,
        "triceps_seated_barbell_french_press" to R.string.exercise_triceps_seated_barbell_french_press,
        "triceps_underhand_bar_cable_extension" to R.string.exercise_triceps_underhand_bar_cable_extension,
        "triceps_single_arm_cable_extension_overhand" to R.string.exercise_triceps_single_arm_cable_extension_overhand,
        "triceps_single_arm_cable_extension_underhand" to R.string.exercise_triceps_single_arm_cable_extension_underhand,
        "triceps_bench_dip" to R.string.exercise_triceps_bench_dip,
        "triceps_seated_dumbbell_overhead_extension" to R.string.exercise_triceps_seated_dumbbell_overhead_extension,
        "triceps_dumbbell_kickback" to R.string.exercise_triceps_dumbbell_kickback,
        "triceps_dumbbell_french_press" to R.string.exercise_triceps_dumbbell_french_press,
        "triceps_cable_rope_pushdown" to R.string.exercise_triceps_cable_rope_pushdown,
        "triceps_barbell_french_press" to R.string.exercise_triceps_barbell_french_press,
        "triceps_close_grip_bench_press" to R.string.exercise_triceps_close_grip_bench_press,
        "triceps_bar_pushdown_overhand" to R.string.exercise_triceps_bar_pushdown_overhand,

        // Chest
        "chest_push_up" to R.string.exercise_chest_push_up,
        "chest_incline_dumbbell_fly" to R.string.exercise_chest_incline_dumbbell_fly,
        "chest_barbell_declined_bench_press" to R.string.exercise_chest_barbell_declined_bench_press,
        "chest_dumbbell_bench_press" to R.string.exercise_chest_dumbbell_bench_press,
        "chest_barbell_bench_press" to R.string.exercise_chest_barbell_bench_press,
        "chest_press_machine" to R.string.exercise_chest_press_machine,
        "chest_cable_crossover" to R.string.exercise_chest_cable_crossover,
        "chest_flat_dumbbell_fly" to R.string.exercise_chest_flat_dumbbell_fly,
        "chest_incline_barbell_bench_press" to R.string.exercise_chest_incline_barbell_bench_press,
        "chest_peck_deck_fly" to R.string.exercise_chest_peck_deck_fly,
        "chest_dumbbell_declined_bench_press" to R.string.exercise_chest_dumbbell_declined_bench_press,

        // Shoulders
        "shoulders_smith_machine_press" to R.string.exercise_shoulders_smith_machine_press,
        "shoulders_barbell_push_press" to R.string.exercise_shoulders_barbell_push_press,
        "shoulders_dumbbell_lateral_raise" to R.string.exercise_shoulders_dumbbell_lateral_raise,
        "shoulders_dumbbell_front_raise" to R.string.exercise_shoulders_dumbbell_front_raise,
        "shoulders_seated_dumbbell_press" to R.string.exercise_shoulders_seated_dumbbell_press,
        "shoulders_barbell_upright_row" to R.string.exercise_shoulders_barbell_upright_row,
        "shoulders_standing_bent_over_lateral_raise" to R.string.exercise_shoulders_standing_bent_over_lateral_raise,
        "shoulders_single_arm_cable_front_raise" to R.string.exercise_shoulders_single_arm_cable_front_raise,
        "shoulders_high_cable_rear_delt_fly" to R.string.exercise_shoulders_high_cable_rear_delt_fly,
        "shoulders_single_arm_cable_lateral_raise" to R.string.exercise_shoulders_single_arm_cable_lateral_raise,
        "shoulders_dumbbell_push_press" to R.string.exercise_shoulders_dumbbell_push_press,
        "shoulders_barbell_front_raise" to R.string.exercise_shoulders_barbell_front_raise,
        "shoulders_seated_barbell_press" to R.string.exercise_shoulders_seated_barbell_press,
        "shoulders_two_handed_dumbbell_front_raise" to R.string.exercise_shoulders_two_handed_dumbbell_front_raise,

        // Abdominals
        "abs_crunch_machine" to R.string.exercise_abs_crunch_machine,
        "abs_crunch" to R.string.exercise_abs_crunch,
        "abs_oblique_crunch" to R.string.exercise_abs_oblique_crunch,
        "abs_reverse_crunch" to R.string.exercise_abs_reverse_crunch,
        "abs_hanging_leg_raise" to R.string.exercise_abs_hanging_leg_raise,
        "abs_rope_pulldown" to R.string.exercise_abs_rope_pulldown,
        "abs_plank_get_up" to R.string.exercise_abs_plank_get_up,
        "abs_plank" to R.string.exercise_abs_plank,
        "abs_wheel_rollout" to R.string.exercise_abs_wheel_rollout,
        "abs_decline_leg_raise" to R.string.exercise_abs_decline_leg_raise,

        // Legs
        "legs_extension" to R.string.exercise_legs_extension,
        "legs_barbell_squat" to R.string.exercise_legs_barbell_squat,
        "legs_barbell_romanian_deadlift" to R.string.exercise_legs_barbell_romanian_deadlift,
        "legs_glute_ham_raise" to R.string.exercise_legs_glute_ham_raise,
        "legs_standing_resistance_band_kickback" to R.string.exercise_legs_standing_resistance_band_kickback,
        "legs_lunge" to R.string.exercise_legs_lunge,
        "legs_barbell_good_morning" to R.string.exercise_legs_barbell_good_morning,
        "legs_dumbbell_goblet_squat" to R.string.exercise_legs_dumbbell_goblet_squat,
        "legs_dumbbell_step_up" to R.string.exercise_legs_dumbbell_step_up,
        "legs_donkey_kicks" to R.string.exercise_legs_donkey_kicks,
        "legs_hack_squat" to R.string.exercise_legs_hack_squat,
        "legs_lying_leg_curl" to R.string.exercise_legs_lying_leg_curl,
        "legs_seated_leg_curl" to R.string.exercise_legs_seated_leg_curl,
        "legs_kettlebell_swing" to R.string.exercise_legs_kettlebell_swing,
        "legs_side_lying_leg_raise" to R.string.exercise_legs_side_lying_leg_raise,
        "legs_banded_glute_bridge" to R.string.exercise_legs_banded_glute_bridge,
        "legs_bodyweight_glute_bridge" to R.string.exercise_legs_bodyweight_glute_bridge,
        "legs_single_leg_extension" to R.string.exercise_legs_single_leg_extension,
        "legs_lateral_band_walk" to R.string.exercise_legs_lateral_band_walk,
        "legs_barbell_front_squat" to R.string.exercise_legs_barbell_front_squat,
        "legs_dumbbell_romanian_deadlift" to R.string.exercise_legs_dumbbell_romanian_deadlift,
        "legs_bodyweight_squat" to R.string.exercise_legs_bodyweight_squat,
        "legs_medicine_ball_squat" to R.string.exercise_legs_medicine_ball_squat,
        "legs_barbell_bulgarian_split_squat" to R.string.exercise_legs_barbell_bulgarian_split_squat,
        "legs_kettlebell_sumo_deadlift" to R.string.exercise_legs_kettlebell_sumo_deadlift,
        "legs_single_leg_bodyweight_deadlift" to R.string.exercise_legs_single_leg_bodyweight_deadlift,
        "legs_barbell_hip_thrust" to R.string.exercise_legs_barbell_hip_thrust,
        "legs_mini_band_squat" to R.string.exercise_legs_mini_band_squat,
        "legs_cable_hip_abduction" to R.string.exercise_legs_cable_hip_abduction,
        "legs_jump_squat" to R.string.exercise_legs_jump_squat,
        "legs_wall_sit" to R.string.exercise_legs_wall_sit,
        "legs_banded_clamshell" to R.string.exercise_legs_banded_clamshell,
        "legs_bodyweight_bulgarian_split_squat" to R.string.exercise_legs_bodyweight_bulgarian_split_squat,
        "legs_leg_press" to R.string.exercise_legs_leg_press,
        "legs_medicine_ball_deadlift" to R.string.exercise_legs_medicine_ball_deadlift,
        "legs_seated_calf_raise" to R.string.exercise_legs_seated_calf_raise,
        "legs_standing_calf_raise" to R.string.exercise_legs_standing_calf_raise


    )
    fun getName(nameKey: String): Int {
        return nameMap[nameKey] ?: R.string.unknown_exercise
    }
}


object WorkoutPlanNameMapper {
    private val nameMap: Map<String, Int> = mapOf(
        // Встроенные планы тренировок
        WorkoutPlanKeys.FULL_BODY_FIRST to R.string.muscle_chest,

        // PPL
        WorkoutPlanKeys.PPL_PUSH_FIRST to R.string.workout_ppl_push_first,
        WorkoutPlanKeys.PPL_PULL_FIRST to R.string.workout_ppl_pull_first,
        WorkoutPlanKeys.PPL_LEGS_FIRST to R.string.workout_ppl_legs_first,

        // Upper Lower
        WorkoutPlanKeys.UPPER_FIRST to R.string.workout_upper_first,
        WorkoutPlanKeys.LOWER_FIRST to R.string.workout_lower_first
    )

    fun getName(nameId: String): Int {
        return nameMap[nameId] ?: R.string.no_muscle_group_selected
    }
}

object WorkoutPlanIconMapper {
    private val iconMap: Map<String, Int> = mapOf(
        WorkoutPlanKeys.FULL_BODY_FIRST to R.drawable.icon_banner_workout_plan,

        // PPL
        WorkoutPlanKeys.PPL_PUSH_FIRST to R.drawable.image_workout_plan_ppl_push_01,
        WorkoutPlanKeys.PPL_PULL_FIRST to R.drawable.image_workout_plan_ppl_pull_01,
        WorkoutPlanKeys.PPL_LEGS_FIRST to R.drawable.image_workout_plan_ppl_legs_01,

        // Upper Lower
        WorkoutPlanKeys.UPPER_FIRST to R.drawable.image_workout_plan_upper_01,
        WorkoutPlanKeys.LOWER_FIRST to R.drawable.image_workout_plan_lower_01,
    )

    fun getName(nameKey: String): Int {
        return iconMap[nameKey] ?: R.drawable.icon_banner_workout_plan
    }
}

// Утилитарная функция для получения отображаемого имени плана (Display Name for UI)
fun getWorkoutPlanDisplayName(
    context: Context,
    workoutPlanNameId: String?,
    workoutPlanName: String?,
): String {
    return when {
        // Пользовательский план - используем название как есть
        !workoutPlanName.isNullOrEmpty() -> {
            workoutPlanName
        }

        // Встроенный план - используем строковый ресурс
        !workoutPlanNameId.isNullOrEmpty() -> {
            context.getString(WorkoutPlanNameMapper.getName(workoutPlanNameId))
        }

        // Резервный вариант
        else -> {
            "Enter the name"
        }
    }
}
